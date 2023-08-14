plugins {
    kotlin("multiplatform").apply(false)
    id("com.android.application").apply(false)
    id("com.android.library").apply(false)
    id("org.jetbrains.compose").apply(false)
}

buildscript {
    dependencies {
        classpath("dev.icerock.moko:resources-generator:0.23.0")
        classpath("org.jetbrains.kotlin:kotlin-serialization:${libs.versions.ktor}")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
