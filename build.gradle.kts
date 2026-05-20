import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.koin.compiler) apply false
    alias(libs.plugins.ktlint.gradle) apply false
}

subprojects {
    pluginManager.withPlugin("com.android.application") {
        apply(plugin = "org.jlleitschuh.gradle.ktlint")
    }
    pluginManager.withPlugin("com.android.library") {
        apply(plugin = "org.jlleitschuh.gradle.ktlint")
    }

    pluginManager.withPlugin("org.jlleitschuh.gradle.ktlint") {
        dependencies {
            add("ktlintRuleset", "io.nlopez.compose.rules:ktlint:0.4.28")
        }

        extensions.configure(KtlintExtension::class.java) {
            version.set("1.8.0")
            android.set(true)
            ignoreFailures.set(false)
            filter {
                exclude("**/build/**")
                exclude("**/generated/**")
            }
        }
    }

    pluginManager.withPlugin("com.android.application") {
        tasks.named("preBuild").configure {
            dependsOn("ktlintFormat")
        }
    }
    pluginManager.withPlugin("com.android.library") {
        tasks.named("preBuild").configure {
            dependsOn("ktlintFormat")
        }
    }
}
