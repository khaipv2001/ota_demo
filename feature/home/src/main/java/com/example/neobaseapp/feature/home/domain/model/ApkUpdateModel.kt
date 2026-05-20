package com.example.neobaseapp.feature.home.domain.model

data class ApkUpdateModel(
    val latestVersionCode: Int,
    val latestVersionName: String,
    val minSupportedVersionCode: Int,
    val apkUrl: String,
    val releaseNotes: String,
    val checksumSha256: String,
    val publishedAt: String,
    val isUpdateAvailable: Boolean,
    val isForceUpdate: Boolean,
)
