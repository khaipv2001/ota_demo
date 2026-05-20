package com.example.otademo.feature.home.di

import com.example.otademo.feature.home.domain.usecase.CheckApkUpdateUseCase
import org.koin.dsl.module

val homeUseCaseModule =
    module {
        single {
            CheckApkUpdateUseCase(apkUpdateRepository = get())
        }
    }
