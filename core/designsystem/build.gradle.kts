plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.bahadori.designsystem"
    compileSdk = 33

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "com.bahadori.testing.MetTestRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

}

dependencies {
    api(project(":core:common"))

    api(libs.androidx.core.ktx)
    api(libs.androidx.appcompat)
    api(libs.google.android.material)

    val composeBom = platform(libs.androidx.compose.bom)
    api(composeBom)
    androidTestApi(composeBom)
    api(libs.androidx.compose.animation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material3)
    api(libs.androidx.compose.materialWindow)
    api(libs.androidx.compose.runtime.livedata)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.hilt.navigation.compose)
    api(libs.coil.kt.compose)
    debugApi(libs.androidx.compose.ui.test.manifest)
    debugApi(libs.androidx.compose.ui.tooling)
    api(libs.androidx.lifecycle.viewModelCompose)
    api(libs.androidx.lifecycle.runtime.compose)
    api(libs.androidx.navigation.compose)
    implementation(libs.accompanist.systemuicontroller)
    api(libs.androidx.lifecycle.viewmodel.ktx)
    api(libs.androidx.lifecycle.viewmodel.savedstate)
    api(libs.androidx.lifecycle.livedata.ktx)
    api(libs.androidx.window)
    androidTestApi(libs.androidx.compose.ui.test)
    androidTestApi(libs.androidx.compose.ui.test.junit4)
}