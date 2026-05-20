package com.example.neobaseapp.feature.updater.di

import com.example.neobaseapp.feature.updater.domain.usecase.CheckForUpdateUseCase
import org.koin.dsl.module

val domainModule =
    module {
        factory { CheckForUpdateUseCase(repository = get()) }
    }
