package com.example.otademo.feature.home.di

import com.example.otademo.feature.home.presentation.ui.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeViewModelModule =
    module {
        viewModelOf(::HomeViewModel)
    }
