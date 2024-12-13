plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.projet_tdm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.projet_tdm"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    // Compose UI
    implementation("androidx.compose.ui:ui:1.5.1")
    implementation("androidx.compose.ui:ui-tooling:1.5.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.1")

    // Material3
    implementation("androidx.compose.material3:material3:1.1.0")

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.5.3")

    // Kotlin standard library
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.8.0")

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("androidx.navigation:navigation-compose:2.6.0")
    implementation("androidx.compose.ui:ui-text:1.5.1")
    implementation("androidx.compose.material3:material3:1.1.0")
    implementation("com.google.android.material:material:1.9.0")
    implementation ("androidx.activity:activity-compose:1.3.1")
    implementation ("androidx.compose.material3:material3:<latest_version>")
    implementation ("androidx.compose.ui:ui:<latest_version>")
    implementation ("androidx.compose.material3:material3:<latest_version>")
    implementation ("androidx.compose.ui:ui-tooling-preview:<latest_version>")

}