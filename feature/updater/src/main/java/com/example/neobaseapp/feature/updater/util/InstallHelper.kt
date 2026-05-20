package com.example.neobaseapp.feature.updater.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

object InstallHelper {
    fun installDownloadedApk(
        context: Context,
        downloadedUri: String,
    ): Result<Unit> =
        runCatching {
            val installUri = buildInstallUri(context, downloadedUri)
            val installIntent =
                Intent(Intent.ACTION_VIEW)
                    .setDataAndType(installUri, "application/vnd.android.package-archive")
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

            try {
                context.startActivity(installIntent)
            } catch (exception: ActivityNotFoundException) {
                throw IllegalStateException("No installer app available to open APK", exception)
            }
        }

    private fun buildInstallUri(
        context: Context,
        downloadedUri: String,
    ): Uri {
        val parsedUri = Uri.parse(downloadedUri)
        return if (parsedUri.scheme.equals("file", ignoreCase = true)) {
            FileProvider.getUriForFile(
                context,
                "${context.packageName}.updater.fileprovider",
                File(requireNotNull(parsedUri.path)),
            )
        } else {
            parsedUri
        }
    }
}
