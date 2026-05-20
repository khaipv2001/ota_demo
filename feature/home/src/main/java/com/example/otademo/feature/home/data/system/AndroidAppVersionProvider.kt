package com.example.otademo.feature.home.data.system

import android.content.Context
import android.os.Build
import com.example.otademo.feature.home.domain.model.AppVersionProvider

class AndroidAppVersionProvider(
    private val context: Context,
) : AppVersionProvider {

    override val versionCode: Int
        get() {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                packageInfo.longVersionCode.toInt()
            } else {
                @Suppress("DEPRECATION")
                packageInfo.versionCode
            }
        }
}
