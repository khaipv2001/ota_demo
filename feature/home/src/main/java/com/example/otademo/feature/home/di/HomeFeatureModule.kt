package com.example.otademo.feature.home.di

import org.koin.dsl.module

val homeFeatureModule =
    module {
        includes(
            homeNetworkModule,
            homeSystemModule,
            homeRepositoryModule,
            homeUseCaseModule,
            homeViewModelModule,
        )
    }
