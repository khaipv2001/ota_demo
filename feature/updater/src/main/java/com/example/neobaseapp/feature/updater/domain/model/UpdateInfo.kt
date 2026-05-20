package com.example.neobaseapp.feature.updater.domain.model

data class UpdateInfo(
    val versionCode: Int,
    val versionName: String,
    val releaseNotes: String,
    val downloadUrl: String,
    val isForceUpdate: Boolean,
)
