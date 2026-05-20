package com.example.otademo.di

import com.example.otademo.feature.home.di.homeFeatureModule
import com.example.otademo.feature.updater.di.updaterFeatureModule
import org.koin.dsl.module

val appModule =
    module {
        includes(
            homeFeatureModule,
            updaterFeatureModule,
        )
    }
