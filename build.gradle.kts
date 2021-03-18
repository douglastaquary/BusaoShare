buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-alpha10")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.4.31")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven {
            url = uri("https://kotlin.bintray.com/kotlinx/")
            url = uri("https://dl.bintray.com/ekito/koin")
            url = uri("https://jitpack.io")
            url = uri("https://oss.sonatype.org/content/repositories/snapshots")
        }
    }
}