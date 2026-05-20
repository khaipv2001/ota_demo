package com.example.neobaseapp.feature.home.di

import com.example.neobaseapp.feature.home.domain.usecase.CheckApkUpdateUseCase
import org.koin.dsl.module

val homeUseCaseModule =
    module {
        single {
            CheckApkUpdateUseCase(apkUpdateRepository = get())
        }
    }
