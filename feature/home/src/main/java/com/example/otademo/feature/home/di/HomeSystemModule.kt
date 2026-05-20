package com.example.otademo.feature.home.di

import com.example.otademo.feature.home.data.system.AndroidAppVersionProvider
import com.example.otademo.feature.home.domain.model.AppVersionProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val homeSystemModule =
    module {
        single<AppVersionProvider> {
            AndroidAppVersionProvider(context = androidContext())
        }
    }
