package com.example.otademo.core.updater.presentation.state

import com.example.otademo.core.updater.domain.model.DownloadState
import com.example.otademo.core.updater.domain.model.UpdateInfo

data class UpdateUIState(
    val isCheckingUpdate: Boolean = false,
    val updateInfo: UpdateInfo? = null,
    val isUpdateVisible: Boolean = false,
    val downloadState: DownloadState = DownloadState.Idle,
    val errorMessage: String? = null,
)
