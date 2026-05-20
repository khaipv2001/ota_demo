package com.example.neobaseapp.feature.updater.domain.repository

import com.example.neobaseapp.feature.updater.domain.model.DownloadState
import com.example.neobaseapp.feature.updater.domain.model.UpdateInfo
import kotlinx.coroutines.flow.Flow

interface AppUpdateRepository {
    suspend fun checkForUpdate(currentVersionCode: Int): Result<UpdateInfo?>

    fun download(updateInfo: UpdateInfo): Flow<DownloadState>
}
