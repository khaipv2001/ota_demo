package com.example.neobaseapp.feature.home.data.repository

import com.example.neobaseapp.core.util.Result
import com.example.neobaseapp.feature.home.data.model.toDomain
import com.example.neobaseapp.feature.home.data.remote.ApkUpdateApi
import com.example.neobaseapp.feature.home.domain.model.ApkUpdateModel
import com.example.neobaseapp.feature.home.domain.repository.ApkUpdateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ApkUpdateRepositoryImpl(
    private val api: ApkUpdateApi,
) : ApkUpdateRepository {
    override fun getLatestRelease(currentVersionCode: Int): Flow<Result<ApkUpdateModel>> =
        flow<Result<ApkUpdateModel>> {
            val apkUpdate = api.getLatestRelease().toDomain(currentVersionCode)
            emit(Result.Success(apkUpdate))
        }.catch { exception ->
            emit(Result.Error(exception))
        }.flowOn(Dispatchers.IO)
}
