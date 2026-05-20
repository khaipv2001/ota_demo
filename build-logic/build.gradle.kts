plugins {
    `kotlin-dsl`
    alias(libs.plugins.ktlint.gradle)
}

kotlin {
    jvmToolchain(17)
}

ktlint {
    version.set(libs.versions.ktlintCli.get())
    android.set(true)
    ignoreFailures.set(false)
    filter {
        exclude("**/build/**")
        exclude("**/generated/**")
    }
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "base.android.application"
            implementationClass = "AndroidAppPlugin"
        }
        register("androidLibrary") {
            id = "base.android.library"
            implementationClass = "AndroidLibPlugin"
        }
        register("androidCompose") {
            id = "base.android.compose"
            implementationClass = "ComposePlugin"
        }
        register("androidKoin") {
            id = "base.android.koin"
            implementationClass = "KoinPlugin"
        }
        register("ktlint") {
            id = "base.ktlint"
            implementationClass = "KtlintPlugin"
        }
    }
}

dependencies {
    implementation(libs.android.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.ksp.gradle.plugin)
    implementation(libs.koin.gradle.plugin)
    implementation(libs.ktlint.gradle.plugin)
}
