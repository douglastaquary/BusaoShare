pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
    plugins {
        id("org.jetbrains.compose").version("1.4.3").apply(false)
    }
}

rootProject.name = "BusaoShare"
include(":androidApp")
include(":common")