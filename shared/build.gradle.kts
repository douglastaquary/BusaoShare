import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("org.jetbrains.kotlin.native.cocoapods")
    id("com.chromaticnoise.multiplatform-swiftpackage") version "2.0.3"
}


// workaround for https://youtrack.jetbrains.com/issue/KT-43944
android {
    configurations {
        create("androidTestApi")
        create("androidTestDebugApi")
        create("androidTestReleaseApi")
        create("testApi")
        create("testDebugApi")
        create("testReleaseApi")
    }
}

kotlin {
    targets {
        ios()
        //macosX64("macOS")
        android()
    }

    cocoapods {
        // Configure fields required by CocoaPods.
        summary = "BusaoShare common module"
        homepage = "homepage placeholder"
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3-native-mt")

                implementation("io.ktor:ktor-client-core:1.5.2")
                implementation("io.ktor:ktor-client-json:1.5.2")
                implementation("io.ktor:ktor-client-logging:1.5.2")
                implementation("io.ktor:ktor-client-serialization:1.5.2")

                // Serialize
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.1")

                // Kodein-DB
                //api("org.kodein.db:kodein-db:0.6.0-beta-kx.ser-1.0.1-126-SNAPSHOT")
                //api("org.kodein.db:kodein-db-serializer-kotlinx:0.6.0-beta-kx.ser-1.0.1-126-SNAPSHOT")

                // kermit
                api("co.touchlab:kermit:0.1.8")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:1.5.2")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:1.5.2")
            }
        }
//        val macOSMain by getting {
//            dependencies {
//                implementation("io.ktor:ktor-client-ios:1.5.2")
//            }
//        }
    }
}

android {
    compileSdkVersion(29)
    buildToolsVersion("29.0.3")

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)
    }

    buildTypes {
        getByName("release") {
            minifyEnabled(false)
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        useIR = true
    }
}

// CocoaPods requires the podspec to have a version.
version = "1.0.0"

val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
    val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
    val framework = kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}

tasks.getByName("build").dependsOn(packForXcode)

multiplatformSwiftPackage {
    packageName("BusaoShare")
    swiftToolsVersion("5.3")
    targetPlatforms {
        iOS { v("13") }
    }
}