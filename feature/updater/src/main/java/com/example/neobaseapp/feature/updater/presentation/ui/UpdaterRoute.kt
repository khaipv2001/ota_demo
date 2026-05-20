package com.example.neobaseapp.feature.updater.presentation.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.neobaseapp.feature.updater.presentation.viewmodel.UpdaterViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun UpdaterRoute(content: @Composable (onCheckUpdate: () -> Unit) -> Unit) {
    val viewModel: UpdaterViewModel = koinViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    content(viewModel::checkForUpdate)

    UpdaterDialog(
        uiState = uiState.value,
        onDismiss = viewModel::dismissDialog,
        onUpdateNow = viewModel::startDownload,
    )
}
