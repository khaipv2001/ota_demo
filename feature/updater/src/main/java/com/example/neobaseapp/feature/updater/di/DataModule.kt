package com.example.neobaseapp.feature.updater.di

import com.example.neobaseapp.feature.updater.data.downloader.AndroidApkDownloader
import com.example.neobaseapp.feature.updater.data.repository.AppUpdateRepositoryImpl
import com.example.neobaseapp.feature.updater.data.system.AndroidAppVersionProvider
import com.example.neobaseapp.feature.updater.domain.repository.AppUpdateRepository
import com.example.neobaseapp.feature.updater.domain.repository.AppVersionProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule =
    module {
        single { AndroidApkDownloader(context = androidContext()) }
        single<AppUpdateRepository> { AppUpdateRepositoryImpl(api = get(), downloader = get()) }
        single<AppVersionProvider> { AndroidAppVersionProvider(context = androidContext()) }
    }
