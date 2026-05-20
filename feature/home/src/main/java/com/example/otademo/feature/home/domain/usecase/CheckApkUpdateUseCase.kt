package com.example.otademo.feature.home.domain.usecase

import com.example.otademo.core.util.Result
import com.example.otademo.feature.home.domain.model.ApkUpdateModel
import com.example.otademo.feature.home.domain.repository.ApkUpdateRepository
import kotlinx.coroutines.flow.Flow

class CheckApkUpdateUseCase(
    private val apkUpdateRepository: ApkUpdateRepository,
) {
    operator fun invoke(currentVersionCode: Int): Flow<Result<ApkUpdateModel>> {
        return apkUpdateRepository.getLatestRelease(currentVersionCode)
    }
}
