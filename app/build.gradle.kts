plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.mindvalley.mindvalleyapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mindvalley.mindvalleyapp"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.mindvalley.mindvalleyapp.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
//    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Dagger - Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    kapt(libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.retrofit2.kotlin.coroutines.adapter)
    implementation(libs.logging.interceptor)

    // kotlin coroutines
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test)

    // GSON
    implementation(libs.gson)

    // Timber for logging
    implementation(libs.timber)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    kapt("androidx.room:room-compiler:2.6.1")

    // Coil image
    implementation(libs.coil)

    // FLow layout
    implementation(libs.accompanist.flowlayout)

    // Swipe to refresh
    implementation(libs.accompanist.swiperefresh)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Local unit tests
    testImplementation(libs.androidx.core)
    testImplementation(libs.junit)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.kotlinx.coroutines.test.v151)
    testImplementation(libs.truth)
    testImplementation(libs.mockwebserver)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.androidx.core.testing)
//    testImplementation(libs.mockk)
    debugImplementation(libs.ui.test.manifest)

    // Instrumentation tests
    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.android.compiler.v237)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.core.testing.v210)
    androidTestImplementation(libs.truth)
    androidTestImplementation(libs.androidx.junit.v113)
    androidTestImplementation(libs.core.ktx)
    androidTestImplementation(libs.mockwebserver)
//    androidTestImplementation(libs.mockk.android)
    androidTestImplementation(libs.androidx.runner)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}