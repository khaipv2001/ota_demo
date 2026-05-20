package com.example.neobaseapp.feature.updater.data.repository

import com.example.neobaseapp.feature.updater.data.downloader.AndroidApkDownloader
import com.example.neobaseapp.feature.updater.data.model.toDomain
import com.example.neobaseapp.feature.updater.data.remote.AppUpdateApi
import com.example.neobaseapp.feature.updater.domain.model.DownloadState
import com.example.neobaseapp.feature.updater.domain.model.UpdateInfo
import com.example.neobaseapp.feature.updater.domain.repository.AppUpdateRepository
import kotlinx.coroutines.flow.Flow

class AppUpdateRepositoryImpl(
    private val api: AppUpdateApi,
    private val downloader: AndroidApkDownloader,
) : AppUpdateRepository {
    override suspend fun checkForUpdate(currentVersionCode: Int): Result<UpdateInfo?> =
        runCatching {
            api.checkForUpdate().toDomain(currentVersionCode)
        }

    override fun download(updateInfo: UpdateInfo): Flow<DownloadState> = downloader.download(updateInfo)
}
