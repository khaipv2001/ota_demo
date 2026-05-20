package com.example.otademo.core.updater.presentation.di

import com.example.otademo.core.updater.data.downloader.AndroidApkDownloader
import com.example.otademo.core.updater.data.repository.AppUpdateRepositoryImpl
import com.example.otademo.core.updater.data.system.AndroidAppVersionProvider
import com.example.otademo.core.updater.domain.repository.ApkDownloader
import com.example.otademo.core.updater.domain.repository.AppUpdateRepository
import com.example.otademo.core.updater.domain.repository.AppVersionProvider
import com.example.otademo.core.updater.domain.usecase.CheckForUpdateUseCase
import com.example.otademo.core.updater.presentation.UpdaterController
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val updaterModule =
    module {
        single<AppUpdateRepository> {
            AppUpdateRepositoryImpl(api = get())
        }
        single<ApkDownloader> {
            AndroidApkDownloader(context = androidContext())
        }
        single<AppVersionProvider> {
            AndroidAppVersionProvider(context = androidContext())
        }
        single {
            CheckForUpdateUseCase(repository = get())
        }
        factory { (scope: CoroutineScope) ->
            UpdaterController(
                scope = scope,
                checkForUpdateUseCase = get(),
                apkDownloader = get(),
                appVersionProvider = get(),
            )
        }
    }
