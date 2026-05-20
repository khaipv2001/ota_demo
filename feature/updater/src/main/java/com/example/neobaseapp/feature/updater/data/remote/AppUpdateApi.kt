package com.example.neobaseapp.feature.updater.data.remote

import com.example.neobaseapp.feature.updater.data.model.UpdateInfoDto
import retrofit2.http.GET

interface AppUpdateApi {
    @GET("api/releases/latest")
    suspend fun checkForUpdate(): UpdateInfoDto
}
