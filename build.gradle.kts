buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.5.30")
        classpath("com.android.tools.build:gradle:7.1.3")
        classpath("com.rickclephas.kmp:kmp-nativecoroutines-gradle-plugin:0.12.1-new-mm")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/ktor/eap")
        maven(url = "https://maven.pkg.jetbrains.space/public/p/kotlinx-coroutines/maven")
        maven(url = "https://androidx.dev/snapshots/builds/7909927/artifacts/repository")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}