package com.example.otademo.core.updater.data.repository

import com.example.otademo.core.updater.data.model.toDomain
import com.example.otademo.core.updater.data.remote.AppUpdateApi
import com.example.otademo.core.updater.domain.model.UpdateInfo
import com.example.otademo.core.updater.domain.repository.AppUpdateRepository

class AppUpdateRepositoryImpl(
    private val api: AppUpdateApi,
) : AppUpdateRepository {

    override suspend fun checkForUpdate(currentVersionCode: Int): Result<UpdateInfo?> =
        runCatching {
            api.checkForUpdate().toDomain(currentVersionCode)
        }
}
