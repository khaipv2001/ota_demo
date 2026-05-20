import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KoinPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("io.insert-koin.compiler.plugin")

            val catalog = catalog()
            dependencies {
                add("implementation", platform(catalog.lib("koin-bom")))
                add("implementation", catalog.lib("koin-core"))
                add("implementation", catalog.lib("koin-android"))
                add("implementation", catalog.lib("koin-androidx-compose"))
                add("implementation", catalog.lib("koin-annotations"))
            }

            extensions.findByName("koinCompiler")?.apply {
                setBooleanProperty("compileSafety", true)
                setBooleanProperty("userLogs", true)
                setBooleanProperty("debugLogs", false)
                setBooleanProperty("unsafeDslChecks", true)
            }
        }
    }
}
