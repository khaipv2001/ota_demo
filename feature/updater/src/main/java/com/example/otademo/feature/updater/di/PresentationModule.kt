package com.example.otademo.feature.updater.di

import com.example.otademo.feature.updater.presentation.viewmodel.UpdaterViewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val presentationModule =
    module {
        viewModel<UpdaterViewModel>()
    }
