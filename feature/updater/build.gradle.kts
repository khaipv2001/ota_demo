import java.util.Properties

plugins {
    id("base.android.library")
    id("base.android.compose")
    id("base.android.koin")
    id("base.ktlint")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.neobaseapp.feature.updater"

    defaultConfig {
        val localProperties = Properties()
        val localPropertiesFile = rootProject.file("local.properties")
        if (localPropertiesFile.exists()) {
            localPropertiesFile.inputStream().use { localProperties.load(it) }
        }
        val apkUpdateBaseUrl = localProperties.getProperty("APK_UPDATE_BASE_URL") ?: "https://example.com/"
        buildConfigField("String", "APK_UPDATE_BASE_URL", "\"$apkUpdateBaseUrl\"")
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.kotlinx.serialization)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.timber)
}
