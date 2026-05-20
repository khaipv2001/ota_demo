package com.example.neobaseapp.feature.updater.di

import com.example.neobaseapp.feature.updater.presentation.viewmodel.UpdaterViewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val presentationModule =
    module {
        viewModel<UpdaterViewModel>()
    }
