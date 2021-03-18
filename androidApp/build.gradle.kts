plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.2")
}

android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.3")

    defaultConfig {
        applicationId = "com.douglastaquary.busaoshare.androidApp"
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-beta02"
    }

    buildFeatures {
        compose = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
            freeCompilerArgs = listOf("-Xallow-jvm-ir-dependencies", "-Xskip-prerelease-check",
                "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi"
            )
        }
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.0")

    implementation("androidx.compose.ui:ui:1.0.0-beta02")
    implementation("androidx.compose.ui:ui-graphics:1.0.0-beta02")
    implementation("androidx.compose.ui:ui-tooling:1.0.0-beta02")
    implementation( "androidx.compose.foundation:foundation-layout:1.0.0-beta02")
    implementation("androidx.compose.material:material:1.0.0-beta02")
    implementation("androidx.compose.runtime:runtime-livedata:1.0.0-beta02")
    implementation("androidx.navigation:navigation-compose:1.0.0-alpha09")
    implementation("dev.chrisbanes.accompanist:accompanist-coil:0.6.2")

    implementation("io.insert-koin:koin-core:3.0.1-beta-1")
    implementation("io.insert-koin:koin-android:3.0.1-beta-1")
    implementation("io.insert-koin:koin-androidx-compose:3.0.1-beta-1")

    testImplementation("junit:junit:4.13")
    androidTestImplementation("androidx.test:runner:1.2.0")

    implementation(project(":shared"))
}
