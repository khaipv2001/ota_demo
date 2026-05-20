import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jlleitschuh.gradle.ktlint.KtlintExtension

class KtlintPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jlleitschuh.gradle.ktlint")

            val catalog = catalog()
            dependencies {
                add("ktlintRuleset", catalog.lib("compose-rules-ktlint"))
            }

            extensions.configure<KtlintExtension> {
                version.set(catalog.findVersion("ktlintCli").get().requiredVersion)
                android.set(true)
                ignoreFailures.set(false)
                filter {
                    exclude("**/build/**")
                    exclude("**/generated/**")
                }
            }

            tasks.matching { it.name == "preBuild" }.configureEach {
                dependsOn("ktlintFormat")
            }
        }
    }
}
