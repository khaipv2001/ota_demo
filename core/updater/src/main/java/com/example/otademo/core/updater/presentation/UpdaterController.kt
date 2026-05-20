package com.example.otademo.core.updater.presentation

import com.example.otademo.core.updater.domain.model.DownloadState
import com.example.otademo.core.updater.domain.repository.ApkDownloader
import com.example.otademo.core.updater.domain.repository.AppVersionProvider
import com.example.otademo.core.updater.domain.usecase.CheckForUpdateUseCase
import com.example.otademo.core.updater.presentation.state.UpdateUIState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UpdaterController(
    private val scope: CoroutineScope,
    private val checkForUpdateUseCase: CheckForUpdateUseCase,
    private val apkDownloader: ApkDownloader,
    private val appVersionProvider: AppVersionProvider,
) {

    private val _uiState = MutableStateFlow(UpdateUIState())
    val uiState: StateFlow<UpdateUIState> = _uiState.asStateFlow()

    private var checkUpdateJob: Job? = null
    private var downloadJob: Job? = null

    fun checkForUpdate() {
        if (checkUpdateJob?.isActive == true) return

        checkUpdateJob =
            scope.launch {
                _uiState.update {
                    it.copy(
                        isCheckingUpdate = true,
                        errorMessage = null,
                    )
                }

                checkForUpdateUseCase(appVersionProvider.versionCode)
                    .onSuccess { updateInfo ->
                        _uiState.update {
                            it.copy(
                                isCheckingUpdate = false,
                                updateInfo = updateInfo,
                                isUpdateVisible = updateInfo != null,
                                downloadState = DownloadState.Idle,
                            )
                        }
                    }
                    .onFailure { throwable ->
                        _uiState.update {
                            it.copy(
                                isCheckingUpdate = false,
                                errorMessage = throwable.message ?: "Check update failed",
                            )
                        }
                    }
            }
    }

    fun startDownload() {
        val currentInfo = uiState.value.updateInfo ?: return
        if (downloadJob?.isActive == true) return

        downloadJob =
            scope.launch {
                apkDownloader.download(currentInfo).collectLatest { downloadState ->
                    _uiState.update { state ->
                        state.copy(
                            downloadState = downloadState,
                            errorMessage = when (downloadState) {
                                is DownloadState.Error -> downloadState.throwable.message
                                else -> state.errorMessage
                            },
                        )
                    }
                }
            }
    }

    fun dismissUpdate() {
        _uiState.update {
            it.copy(isUpdateVisible = false)
        }
    }
}
