package com.example.neobaseapp.di

import com.example.neobaseapp.feature.home.di.homeFeatureModule
import com.example.neobaseapp.feature.updater.di.updaterFeatureModule
import org.koin.dsl.module

val appModule =
    module {
        includes(
            homeFeatureModule,
            updaterFeatureModule,
        )
    }
