package com.example.neobaseapp.feature.updater.data.downloader

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import com.example.neobaseapp.feature.updater.domain.model.DownloadState
import com.example.neobaseapp.feature.updater.domain.model.UpdateInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.File

class AndroidApkDownloader(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {
    fun download(updateInfo: UpdateInfo): Flow<DownloadState> =
        flow {
            val downloadManager =
                context.getSystemService(DownloadManager::class.java)
                    ?: throw IllegalStateException("DownloadManager is not available")

            val fileName = "update-${updateInfo.versionCode}.apk"
            val subPath = "updater/$fileName"
            val destinationRoot =
                context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
                    ?: throw IllegalStateException("External files directory is not available")
            val destinationFile = File(destinationRoot, subPath)
            destinationFile.parentFile?.mkdirs()

            val request =
                DownloadManager
                    .Request(Uri.parse(updateInfo.downloadUrl))
                    .setTitle("App update ${updateInfo.versionName}")
                    .setDescription("Downloading update package")
                    .setAllowedOverMetered(true)
                    .setAllowedOverRoaming(false)
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setDestinationInExternalFilesDir(
                        context,
                        Environment.DIRECTORY_DOWNLOADS,
                        subPath,
                    )

            val downloadId = downloadManager.enqueue(request)
            emit(DownloadState.Downloading(progress = 0))

            var lastProgress = -1
            var isFinished = false

            while (!isFinished) {
                val query = DownloadManager.Query().setFilterById(downloadId)
                downloadManager.query(query).use { cursor ->
                    if (!cursor.moveToFirst()) {
                        emit(DownloadState.Error(IllegalStateException("Download record not found")))
                        isFinished = true
                        return@use
                    }

                    val status = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS))
                    val downloadedBytes =
                        cursor.getLong(
                            cursor.getColumnIndexOrThrow(
                                DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR,
                            ),
                        )
                    val totalBytes =
                        cursor.getLong(
                            cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES),
                        )
                    val progress =
                        if (totalBytes > 0) {
                            ((downloadedBytes * 100) / totalBytes).toInt().coerceIn(0, 100)
                        } else {
                            0
                        }

                    if (progress != lastProgress && status == DownloadManager.STATUS_RUNNING) {
                        emit(DownloadState.Downloading(progress))
                        lastProgress = progress
                    }

                    when (status) {
                        DownloadManager.STATUS_SUCCESSFUL -> {
                            emit(DownloadState.Success(uri = Uri.fromFile(destinationFile).toString()))
                            isFinished = true
                        }

                        DownloadManager.STATUS_FAILED -> {
                            val reason = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_REASON))
                            emit(DownloadState.Error(IllegalStateException("Download failed. reason=$reason")))
                            isFinished = true
                        }
                    }
                }

                if (!isFinished) delay(500)
            }
        }.flowOn(ioDispatcher)
}
