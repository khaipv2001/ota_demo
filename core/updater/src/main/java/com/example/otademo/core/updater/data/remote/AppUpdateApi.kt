package com.example.otademo.core.updater.data.remote

import com.example.otademo.core.updater.data.model.UpdateInfoDto
import retrofit2.http.GET

interface AppUpdateApi {
    @GET("api/releases/latest")
    suspend fun checkForUpdate(): UpdateInfoDto
}
