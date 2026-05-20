package com.example.otademo.feature.updater.domain.usecase

import com.example.otademo.feature.updater.domain.model.UpdateInfo
import com.example.otademo.feature.updater.domain.repository.AppUpdateRepository

class CheckForUpdateUseCase(
    private val repository: AppUpdateRepository,
) {
    suspend operator fun invoke(currentVersionCode: Int): Result<UpdateInfo?> = repository.checkForUpdate(currentVersionCode)
}
