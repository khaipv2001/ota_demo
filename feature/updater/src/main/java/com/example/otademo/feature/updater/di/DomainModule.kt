package com.example.otademo.feature.updater.di

import com.example.otademo.feature.updater.domain.usecase.CheckForUpdateUseCase
import org.koin.dsl.module

val domainModule =
    module {
        factory { CheckForUpdateUseCase(repository = get()) }
    }
