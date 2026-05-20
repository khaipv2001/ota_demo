package com.example.neobaseapp.feature.home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApkUpdateDto(
    @SerialName("latest_version_code")
    val latestVersionCode: Int,
    @SerialName("latest_version_name")
    val latestVersionName: String? = null,
    @SerialName("min_supported_version_code")
    val minSupportedVersionCode: Int? = null,
    @SerialName("apk_url")
    val apkUrl: String? = null,
    @SerialName("release_notes")
    val releaseNotes: String? = null,
    @SerialName("checksum_sha256")
    val checksumSha256: String? = null,
    @SerialName("published_at")
    val publishedAt: String? = null,
)
