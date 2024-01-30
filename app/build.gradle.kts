plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
}

apply(from= "../shared_dependencies.gradle")

android {
    namespace = "com.muhdila.nonton"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.muhdila.nonton"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    buildTypes {
        release {
            buildConfigField("String", "API_TOKEN", "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxOGViMTc3MGQ5MDdlN2YzZmZiM2YzMDJiNzg2MzlmNyIsInN1YiI6IjY1NGI2NjVkMWFjMjkyN2IzMzg5MTg4MyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.9ir8kN05x4oympSnF89p4PqvpuBq7vck4-VjTa3bTIY\"")
        }

        debug {
            buildConfigField("String", "API_TOKEN", "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxOGViMTc3MGQ5MDdlN2YzZmZiM2YzMDJiNzg2MzlmNyIsInN1YiI6IjY1NGI2NjVkMWFjMjkyN2IzMzg5MTg4MyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.9ir8kN05x4oympSnF89p4PqvpuBq7vck4-VjTa3bTIY\"")
        }
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(project(":core"))

}