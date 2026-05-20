package com.example.otademo.feature.updater.data.system

import android.content.Context
import android.os.Build
import com.example.otademo.feature.updater.domain.repository.AppVersionProvider

class AndroidAppVersionProvider(
    private val context: Context,
) : AppVersionProvider {
    override val versionCode: Int
        get() {
            val packageInfo =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    context.packageManager.getPackageInfo(
                        context.packageName,
                        android.content.pm.PackageManager.PackageInfoFlags
                            .of(0),
                    )
                } else {
                    @Suppress("DEPRECATION")
                    context.packageManager.getPackageInfo(context.packageName, 0)
                }
            return packageInfo.longVersionCode.toInt()
        }
}
