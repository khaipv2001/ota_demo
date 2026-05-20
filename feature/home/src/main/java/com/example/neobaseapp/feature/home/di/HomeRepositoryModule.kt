package com.example.neobaseapp.feature.home.di

import com.example.neobaseapp.feature.home.data.repository.ApkUpdateRepositoryImpl
import com.example.neobaseapp.feature.home.domain.repository.ApkUpdateRepository
import org.koin.dsl.module

val homeRepositoryModule =
    module {
        single<ApkUpdateRepository> {
            ApkUpdateRepositoryImpl(api = get())
        }
    }
