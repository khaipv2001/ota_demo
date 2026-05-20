package com.example.neobaseapp.feature.home.domain.repository

import com.example.neobaseapp.core.util.Result
import com.example.neobaseapp.feature.home.domain.model.ApkUpdateModel
import kotlinx.coroutines.flow.Flow

interface ApkUpdateRepository {
    fun getLatestRelease(currentVersionCode: Int): Flow<Result<ApkUpdateModel>>
}
