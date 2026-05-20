package com.example.neobaseapp.feature.updater.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.example.neobaseapp.core.base.BaseViewModel
import com.example.neobaseapp.feature.updater.domain.model.DownloadState
import com.example.neobaseapp.feature.updater.domain.repository.AppUpdateRepository
import com.example.neobaseapp.feature.updater.domain.repository.AppVersionProvider
import com.example.neobaseapp.feature.updater.domain.usecase.CheckForUpdateUseCase
import com.example.neobaseapp.feature.updater.presentation.state.UpdaterUiState
import com.example.neobaseapp.feature.updater.util.InstallHelper
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UpdaterViewModel(
    private val context: Context,
    private val checkForUpdateUseCase: CheckForUpdateUseCase,
    private val appUpdateRepository: AppUpdateRepository,
    private val appVersionProvider: AppVersionProvider,
) : BaseViewModel() {
    private val _uiState = MutableStateFlow(UpdaterUiState())
    val uiState = _uiState.asStateFlow()

    private var checkUpdateJob: Job? = null
    private var downloadJob: Job? = null

    fun checkForUpdate() {
        if (checkUpdateJob?.isActive == true) return

        checkUpdateJob =
            viewModelScope.launch {
                _uiState.update { it.copy(isCheckingUpdate = true, errorMessage = null) }

                checkForUpdateUseCase(appVersionProvider.versionCode)
                    .onSuccess { updateInfo ->
                        _uiState.update {
                            it.copy(
                                isCheckingUpdate = false,
                                updateInfo = updateInfo,
                                isDialogVisible = updateInfo != null,
                                downloadState = DownloadState.Idle,
                            )
                        }
                    }.onFailure { throwable ->
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
            viewModelScope.launch {
                appUpdateRepository.download(currentInfo).collectLatest { downloadState ->
                    _uiState.update { state ->
                        state.copy(
                            downloadState = downloadState,
                            errorMessage =
                                when (downloadState) {
                                    is DownloadState.Error -> downloadState.throwable.message
                                    else -> state.errorMessage
                                },
                        )
                    }

                    if (downloadState is DownloadState.Success) {
                        InstallHelper
                            .installDownloadedApk(context, downloadState.uri)
                            .onFailure { error ->
                                _uiState.update {
                                    it.copy(
                                        errorMessage = error.message ?: "Install failed",
                                    )
                                }
                            }
                    }
                }
            }
    }

    fun dismissDialog() {
        _uiState.update { it.copy(isDialogVisible = false) }
    }
}
