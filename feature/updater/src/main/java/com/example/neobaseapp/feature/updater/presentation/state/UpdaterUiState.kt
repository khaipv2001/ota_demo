package com.example.neobaseapp.feature.updater.presentation.state

import com.example.neobaseapp.feature.updater.domain.model.DownloadState
import com.example.neobaseapp.feature.updater.domain.model.UpdateInfo

data class UpdaterUiState(
    val isCheckingUpdate: Boolean = false,
    val updateInfo: UpdateInfo? = null,
    val isDialogVisible: Boolean = false,
    val downloadState: DownloadState = DownloadState.Idle,
    val errorMessage: String? = null,
)
