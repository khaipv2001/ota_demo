plugins {
    id("base.android.library")
    id("base.ktlint")
}

android {
    namespace = "com.example.neobaseapp.core"
}

dependencies {
    api(libs.androidx.lifecycle.viewmodel.ktx)
}
