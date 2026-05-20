package com.example.neobaseapp.feature.updater.domain.usecase

import com.example.neobaseapp.feature.updater.domain.model.UpdateInfo
import com.example.neobaseapp.feature.updater.domain.repository.AppUpdateRepository

class CheckForUpdateUseCase(
    private val repository: AppUpdateRepository,
) {
    suspend operator fun invoke(currentVersionCode: Int): Result<UpdateInfo?> = repository.checkForUpdate(currentVersionCode)
}
