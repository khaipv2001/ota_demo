package com.example.neobaseapp.feature.updater.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateInfoDto(
    @SerialName("latest_version_code")
    val latestVersionCode: Int,
    @SerialName("latest_version_name")
    val latestVersionName: String? = null,
    @SerialName("release_notes")
    val releaseNotes: String? = null,
    @SerialName("apk_url")
    val apkUrl: String? = null,
    @SerialName("min_supported_version_code")
    val minSupportedVersionCode: Int? = null,
)
