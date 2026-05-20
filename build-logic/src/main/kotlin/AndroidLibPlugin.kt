import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")

            val catalog = catalog()
            extensions.configure<LibraryExtension> {
                compileSdk = catalog.versionAsInt("compileSdkVersion")

                defaultConfig {
                    minSdk = catalog.versionAsInt("minSdkVersion")
                }

                setupJava17()
            }
        }
    }
}
