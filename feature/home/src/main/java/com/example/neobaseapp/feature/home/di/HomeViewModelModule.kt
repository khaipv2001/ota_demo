package com.example.neobaseapp.feature.home.di

import com.example.neobaseapp.feature.home.presentation.ui.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeViewModelModule =
    module {
        viewModelOf(::HomeViewModel)
    }
