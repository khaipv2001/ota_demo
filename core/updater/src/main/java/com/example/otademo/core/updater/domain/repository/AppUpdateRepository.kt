package com.example.otademo.core.updater.domain.repository

import com.example.otademo.core.updater.domain.model.UpdateInfo

interface AppUpdateRepository {
    suspend fun checkForUpdate(currentVersionCode: Int): Result<UpdateInfo?>
}
