package com.example.neobaseapp.feature.home.di

import com.example.neobaseapp.feature.home.BuildConfig
import com.example.neobaseapp.feature.home.data.remote.ApkUpdateApi
import com.example.neobaseapp.feature.home.data.remote.FakeApkUpdateInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val homeNetworkModule =
    module {
        single {
            Json {
                ignoreUnknownKeys = true
                explicitNulls = false
            }
        }

        single {
            HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
            }
        }

        single {
            OkHttpClient
                .Builder()
                .addInterceptor(FakeApkUpdateInterceptor())
                .addInterceptor(get<HttpLoggingInterceptor>())
                .build()
        }

        single {
            Retrofit
                .Builder()
                .baseUrl(BuildConfig.APK_UPDATE_BASE_URL)
                .client(get())
                .addConverterFactory(get<Json>().asConverterFactory("application/json".toMediaType()))
                .build()
        }

        single<ApkUpdateApi> {
            get<Retrofit>().create(ApkUpdateApi::class.java)
        }
    }
