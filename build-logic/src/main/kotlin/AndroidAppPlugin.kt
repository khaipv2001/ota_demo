import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidAppPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")

            val catalog = catalog()
            extensions.configure<ApplicationExtension> {
                compileSdk = catalog.versionAsInt("compileSdkVersion")

                defaultConfig {
                    minSdk = catalog.versionAsInt("minSdkVersion")
                    targetSdk = catalog.versionAsInt("targetSdkVersion")
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                buildFeatures {
                    buildConfig = true
                }

                setupJava17()
            }
        }
    }
}
