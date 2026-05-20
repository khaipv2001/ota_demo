package com.example.neobaseapp.feature.updater.domain.model

sealed interface DownloadState {
    data object Idle : DownloadState

    data class Downloading(
        val progress: Int,
    ) : DownloadState

    data class Success(
        val uri: String,
    ) : DownloadState

    data class Error(
        val throwable: Throwable,
    ) : DownloadState
}
