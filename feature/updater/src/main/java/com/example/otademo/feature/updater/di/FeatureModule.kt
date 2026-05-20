package com.example.otademo.feature.updater.di

import org.koin.dsl.module

val updaterFeatureModule =
    module {
        includes(
            networkModule,
            dataModule,
            domainModule,
            presentationModule,
        )
    }
