package com.example.otademo.feature.home.di

import com.example.otademo.feature.home.data.repository.ApkUpdateRepositoryImpl
import com.example.otademo.feature.home.domain.repository.ApkUpdateRepository
import org.koin.dsl.module

val homeRepositoryModule =
    module {
        single<ApkUpdateRepository> {
            ApkUpdateRepositoryImpl(api = get())
        }
    }
