package com.example.otademo.feature.updater.domain.repository

import com.example.otademo.feature.updater.domain.model.DownloadState
import com.example.otademo.feature.updater.domain.model.UpdateInfo
import kotlinx.coroutines.flow.Flow

interface AppUpdateRepository {
    suspend fun checkForUpdate(currentVersionCode: Int): Result<UpdateInfo?>

    fun download(updateInfo: UpdateInfo): Flow<DownloadState>
}
