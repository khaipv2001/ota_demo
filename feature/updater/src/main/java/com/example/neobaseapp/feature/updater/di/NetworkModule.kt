package com.example.neobaseapp.feature.updater.di

import com.example.neobaseapp.feature.updater.BuildConfig
import com.example.neobaseapp.feature.updater.data.remote.AppUpdateApi
import com.example.neobaseapp.feature.updater.data.remote.FakeAppUpdateInterceptor
import com.example.neobaseapp.feature.updater.di.Qualifiers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val networkModule =
    module {
        single(named(Qualifiers.JSON)) {
            Json {
                ignoreUnknownKeys = true
                explicitNulls = false
            }
        }

        single(named(Qualifiers.LOGGER)) {
            HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
            }
        }

        single(named(Qualifiers.CLIENT)) {
            OkHttpClient
                .Builder()
                .addInterceptor(FakeAppUpdateInterceptor())
                .addInterceptor(get<HttpLoggingInterceptor>(named(Qualifiers.LOGGER)))
                .build()
        }

        single(named(Qualifiers.RETROFIT)) {
            Retrofit
                .Builder()
                .baseUrl(BuildConfig.APK_UPDATE_BASE_URL)
                .client(get<OkHttpClient>(named(Qualifiers.CLIENT)))
                .addConverterFactory(
                    get<Json>(named(Qualifiers.JSON)).asConverterFactory("application/json".toMediaType()),
                ).build()
        }

        single<AppUpdateApi> { get<Retrofit>(named(Qualifiers.RETROFIT)).create(AppUpdateApi::class.java) }
    }
