import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class ComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.plugin.compose")

            pluginManager.withPlugin("com.android.application") {
                extensions.configure<ApplicationExtension> {
                    buildFeatures.compose = true
                }
            }
            pluginManager.withPlugin("com.android.library") {
                extensions.configure<LibraryExtension> {
                    buildFeatures.compose = true
                }
            }

            val catalog = catalog()
            dependencies {
                add("implementation", platform(catalog.lib("androidx-compose-bom")))
                add("implementation", catalog.lib("androidx-compose-ui"))
                add("implementation", catalog.lib("androidx-compose-ui-graphics"))
                add("implementation", catalog.lib("androidx-compose-ui-tooling-preview"))
                add("implementation", catalog.lib("androidx-compose-material3"))
                add("debugImplementation", catalog.lib("androidx-compose-ui-tooling"))
            }
        }
    }
}
