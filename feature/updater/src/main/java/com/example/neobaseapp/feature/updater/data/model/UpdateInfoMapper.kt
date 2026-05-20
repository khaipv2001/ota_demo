package com.example.neobaseapp.feature.updater.data.model

import com.example.neobaseapp.feature.updater.domain.model.UpdateInfo

fun UpdateInfoDto.toDomain(currentVersionCode: Int): UpdateInfo? {
    if (latestVersionCode <= currentVersionCode) return null

    return UpdateInfo(
        versionCode = latestVersionCode,
        versionName = latestVersionName.orEmpty(),
        releaseNotes = releaseNotes.orEmpty(),
        downloadUrl = apkUrl.orEmpty(),
        isForceUpdate = (minSupportedVersionCode ?: currentVersionCode) > currentVersionCode,
    )
}
