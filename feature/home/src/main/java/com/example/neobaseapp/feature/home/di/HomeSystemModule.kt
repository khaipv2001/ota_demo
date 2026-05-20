package com.example.neobaseapp.feature.home.di

import com.example.neobaseapp.feature.home.data.system.AndroidAppVersionProvider
import com.example.neobaseapp.feature.home.domain.model.AppVersionProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val homeSystemModule =
    module {
        single<AppVersionProvider> {
            AndroidAppVersionProvider(context = androidContext())
        }
    }
