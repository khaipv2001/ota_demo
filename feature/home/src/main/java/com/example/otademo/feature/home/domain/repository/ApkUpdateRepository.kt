package com.example.otademo.feature.home.domain.repository

import com.example.otademo.core.util.Result
import com.example.otademo.feature.home.domain.model.ApkUpdateModel
import kotlinx.coroutines.flow.Flow

interface ApkUpdateRepository {
    fun getLatestRelease(currentVersionCode: Int): Flow<Result<ApkUpdateModel>>
}
