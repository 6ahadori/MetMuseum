plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.bahadori.testing"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":core:model"))

    val composeBom = platform(libs.androidx.compose.bom)
    api(composeBom)
    api(libs.junit)
    api(libs.androidx.test.core)
    api(libs.androidx.test.runner)
    api(libs.androidx.test.espresso.core)
    api(libs.androidx.test.rules)
    api(libs.androidx.test.ext.junit)
    api(libs.kotlinx.coroutines.test)
    api(libs.google.truth)
    api(libs.hilt.android.testing)
    api(libs.androidx.compose.ui.test.junit4)
    debugApi(libs.androidx.compose.ui.test.manifest)
    api(libs.robolectric)
    api(libs.google.truth)
    api(libs.mockito.core)
    api(libs.mockito.kotlin)
    api(libs.mockk)
    api(libs.androidx.room.testing)
    api(libs.hilt.android.testing)
}