package com.example.otademo.core.updater.domain.usecase

import com.example.otademo.core.updater.domain.model.UpdateInfo
import com.example.otademo.core.updater.domain.repository.AppUpdateRepository

class CheckForUpdateUseCase(
    private val repository: AppUpdateRepository,
) {
    suspend operator fun invoke(currentVersionCode: Int): Result<UpdateInfo?> {
        return repository.checkForUpdate(currentVersionCode)
    }
}
