package com.example.neobaseapp.feature.home.domain.usecase

import com.example.neobaseapp.core.util.Result
import com.example.neobaseapp.feature.home.domain.model.ApkUpdateModel
import com.example.neobaseapp.feature.home.domain.repository.ApkUpdateRepository
import kotlinx.coroutines.flow.Flow

class CheckApkUpdateUseCase(
    private val apkUpdateRepository: ApkUpdateRepository,
) {
    operator fun invoke(currentVersionCode: Int): Flow<Result<ApkUpdateModel>> = apkUpdateRepository.getLatestRelease(currentVersionCode)
}
