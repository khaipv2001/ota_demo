package com.example.otademo.feature.home.presentation.ui

import androidx.lifecycle.viewModelScope
import com.example.otademo.core.base.BaseViewModel
import com.example.otademo.core.util.onError
import com.example.otademo.core.util.onSuccess
import com.example.otademo.feature.home.domain.model.AppVersionProvider
import com.example.otademo.feature.home.domain.usecase.CheckApkUpdateUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(
    private val checkApkUpdateUseCase: CheckApkUpdateUseCase,
    private val appVersionProvider: AppVersionProvider,
) : BaseViewModel() {

    private var checkUpdateJob: Job? = null

    fun checkUpdate() {
        if (checkUpdateJob?.isActive == true) return

        checkUpdateJob = viewModelScope.launch {
            Timber.d("Checking APK update")
            checkApkUpdateUseCase(appVersionProvider.versionCode).collectLatest { result ->
                result
                    .onSuccess { apkUpdate ->
                        Timber.d(
                            "APK update checked. available=%s force=%s latest=%s url=%s",
                            apkUpdate.isUpdateAvailable,
                            apkUpdate.isForceUpdate,
                            apkUpdate.latestVersionName,
                            apkUpdate.apkUrl,
                        )
                    }
                    .onError { throwable ->
                        Timber.e(throwable, "APK update check failed")
                    }
            }
        }
    }
}
