package com.example.neobaseapp.feature.updater.data.system

import android.content.Context
import android.os.Build
import androidx.core.content.pm.PackageInfoCompat
import com.example.neobaseapp.feature.updater.domain.repository.AppVersionProvider

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
            return PackageInfoCompat.getLongVersionCode(packageInfo).toInt()
        }
}
