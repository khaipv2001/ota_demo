import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.getByType

internal fun Project.catalog(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun VersionCatalog.versionAsInt(alias: String): Int = findVersion(alias).get().requiredVersion.toInt()

internal fun VersionCatalog.lib(alias: String) = findLibrary(alias).get()

internal fun ApplicationExtension.setupJava17() {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

internal fun LibraryExtension.setupJava17() {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

@Suppress("UNCHECKED_CAST")
internal fun Any.setBooleanProperty(
    propertyName: String,
    value: Boolean,
) {
    val setterName = "set" + propertyName.replaceFirstChar { it.uppercaseChar() }
    val setter =
        javaClass.methods.firstOrNull {
            it.name == setterName && it.parameterCount == 1 && it.parameterTypes[0] == Boolean::class.javaPrimitiveType
        }
    if (setter != null) {
        setter.invoke(this, value)
        return
    }

    val property =
        javaClass.methods
            .firstOrNull {
                it.name == propertyName && it.parameterCount == 0
            }?.invoke(this)

    if (property is Property<*>) {
        (property as Property<Boolean>).set(value)
    }
}
