object Versions {
    const val kotlinCoroutines = "1.6.1"
    const val ktor = "2.0.0"
    const val kotlinxSerialization = "1.3.1"
    const val koin = "3.2.0"
    const val lifecycle = "2.2.0-alpha01"
    const val multiplatformSettings = "0.8.1"
    const val kmpNativeCoroutinesVersion = "0.12.1-new-mm"

    const val compose = "1.2.0-alpha08"
    const val navCompose = "2.4.2"
    const val slf4j = "1.7.30"
    const val composeDesktop = "0.0.0-master-dev673"

    const val realm = "0.10.2"

    const val junit = "4.12"

    const val kermit = "1.0.0"
}

object Deps {
    const val realm = "io.realm.kotlin:library-base:${Versions.realm}"

    object AndroidSdk {
        const val min = 21
        const val compile = 31
        const val target = compile
    }

    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val uiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val foundationLayout =
            "androidx.compose.foundation:foundation-layout:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val navigation = "androidx.navigation:navigation-compose:${Versions.navCompose}"
    }

    object Kotlinx {
        const val serializationCore = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinxSerialization}"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    }

    object Ktor {
        const val clientCore = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val clientJson = "io.ktor:ktor-client-json:${Versions.ktor}"
        const val clientLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
        const val clientSerialization = "io.ktor:ktor-client-serialization:${Versions.ktor}"
        const val contentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
        const val json = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"

        const val clientAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
        const val clientJava = "io.ktor:ktor-client-java:${Versions.ktor}"
        const val slf4j = "org.slf4j:slf4j-simple:${Versions.slf4j}"
        const val clientIos = "io.ktor:ktor-client-ios:${Versions.ktor}"
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val test = "io.insert-koin:koin-test:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
        const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    }

    object Log {
        const val slf4j = "org.slf4j:slf4j-simple:${Versions.slf4j}"
        const val kermit = "co.touchlab:kermit:${Versions.kermit}"
    }

    const val multiplatformSettings = "com.russhwolf:multiplatform-settings:${Versions.multiplatformSettings}"
}