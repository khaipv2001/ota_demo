package com.example.neobaseapp.feature.home.data.model

import com.example.neobaseapp.feature.home.domain.model.ApkUpdateModel

fun ApkUpdateDto.toDomain(currentVersionCode: Int) =
    ApkUpdateModel(
        latestVersionCode = latestVersionCode,
        latestVersionName = latestVersionName.orEmpty(),
        minSupportedVersionCode = minSupportedVersionCode ?: currentVersionCode,
        apkUrl = apkUrl.orEmpty(),
        releaseNotes = releaseNotes.orEmpty(),
        checksumSha256 = checksumSha256.orEmpty(),
        publishedAt = publishedAt.orEmpty(),
        isUpdateAvailable = latestVersionCode > currentVersionCode,
        isForceUpdate = (minSupportedVersionCode ?: currentVersionCode) > currentVersionCode,
    )
