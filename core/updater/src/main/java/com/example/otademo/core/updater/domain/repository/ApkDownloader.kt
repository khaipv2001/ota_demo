package com.example.otademo.core.updater.domain.repository

import com.example.otademo.core.updater.domain.model.DownloadState
import com.example.otademo.core.updater.domain.model.UpdateInfo
import kotlinx.coroutines.flow.Flow

interface ApkDownloader {
    fun download(updateInfo: UpdateInfo): Flow<DownloadState>
}
