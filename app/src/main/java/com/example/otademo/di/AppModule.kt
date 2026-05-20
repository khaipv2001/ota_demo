package com.example.otademo.di

import com.example.otademo.feature.home.di.homeFeatureModule
import org.koin.dsl.module

val appModule =
    module {
        includes(homeFeatureModule)
    }
