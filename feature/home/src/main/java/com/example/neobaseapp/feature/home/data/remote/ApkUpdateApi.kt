package com.example.neobaseapp.feature.home.data.remote

import com.example.neobaseapp.feature.home.data.model.ApkUpdateDto
import retrofit2.http.GET

interface ApkUpdateApi {
    @GET("api/releases/latest")
    suspend fun getLatestRelease(): ApkUpdateDto
}
