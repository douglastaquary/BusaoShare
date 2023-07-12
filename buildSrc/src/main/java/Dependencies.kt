object Versions {
    const val androidMinSdk = 21
    const val androidCompileSdk = 33
    const val androidTargetSdk = androidCompileSdk
    const val kmpNativeCoroutinesVersion = "1.0.0-ALPHA-13"

    const val kotlinxHtmlJs = "0.7.3"

    const val kotlin = "1.9.0"
    const val kspPlugin = "1.9.0-1.0.11"
    const val sqlDelight = "1.5.5"
    const val kotlinCoroutines = "1.7.1"

    const val ktor = "2.3.0"
    const val kotlinxSerialization = "1.5.1"
    const val koin = "3.2.0"
    const val lifecycle = "2.2.0-alpha01"
    const val multiplatformSettings = "1.0.0-alpha01"
    const val kmpNativeCoroutines = "1.0.0-ALPHA-13"

    const val material = "1.6.1"
    const val activityCompose = "1.7.1"
    const val lifecycleKtx = "2.6.1"
    const val lifecycleRuntimeKtx = lifecycleKtx
    const val lifecycleViewmodelKtx = lifecycleKtx
    const val horologist = "0.4.8"
    const val composeMaterial3 = "1.1.0"
    const val shadow = "7.0.0"
    const val osmdroidAndroid = "6.1.10"

    const val redwood = "0.5.0"

    const val compose = "1.4.3"
    const val composeCompiler = "1.5.0-dev-k1.9.0-6a60475e07f"
    const val jbComposeCompiler = "1.4.8-beta"

    const val navCompose = "2.5.3"
    const val slf4j = "1.7.30"
    const val composeDesktop = "0.0.0-master-dev673"
    const val jetlime = "1.0.3"

    const val composeDesktopWeb = "1.5.0-dev1074"
    const val composeIos = "1.5.0-dev1074"

    const val realm = "0.10.2"
    const val junit = "4.12"
    const val kermit = "1.0.0"

    const val kotlinReact = "17.0.1-pre.146-kotlin-1.4.30"
    const val kotlinReactDom = "17.0.1-pre.146-kotlin-1.4.30"
    const val kotlinReactRouterDom = "5.1.2-pre.110-kotlin-1.4.0"
    const val kotlinStyled = "5.2.1-pre.146-kotlin-1.4.30"

    const val gradleVersionsPlugin = "0.39.0"
}

object Deps {
    const val realm = "io.realm.kotlin:library-base:${Versions.realm}"

    object Gradle {
        const val shadow = "gradle.plugin.com.github.jengelman.gradle.plugins:shadow:${Versions.shadow}"
        const val sqlDelight = "com.squareup.sqldelight:gradle-plugin:${Versions.sqlDelight}"
        const val gradleVersionsPlugin = "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleVersionsPlugin}"
    }

    object AndroidSdk {
        const val min = 21
        const val compile = 33
        const val target = compile
    }

    object Android {
        const val osmdroidAndroid = "org.osmdroid:osmdroid-android:${Versions.osmdroidAndroid}"
    }

    object Compose {
        const val compiler = "androidx.compose.compiler:compiler:${Versions.composeCompiler}"
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val uiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val foundationLayout =
            "androidx.compose.foundation:foundation-layout:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val navigation = "androidx.navigation:navigation-compose:${Versions.navCompose}"
        const val material3 = "androidx.compose.material3:material3:${Versions.composeMaterial3}"
    }

    object Kotlinx {
        const val serializationCore = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Versions.kotlinxSerialization}"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    }

    object AndroidX {
        const val benchmarkMacroJunit4 = "androidx.benchmark:benchmark-macro-junit4:1.1.0-rc01"
        const val benchmarkJunit4 = "androidx.benchmark:benchmark-junit4:1.1.0-rc01"
        const val lifecycleRuntimeCompose = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycleRuntimeKtx}"
        const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}"
        const val lifecycleViewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewmodelKtx}"
        const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
        const val metrics = "androidx.metrics:metrics-performance:1.0.0-alpha01"
        const val testEspressoCore = "androidx.test.espresso:espresso-core:3.4.0"
        const val testExtJunit = "androidx.test.ext:junit:1.1.3"
        const val testUiautomator = "androidx.test.uiautomator:uiautomator:2.2.0"
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

    object React {
        const val react = "org.jetbrains:kotlin-react:${Versions.kotlinReact}"
        const val dom = "org.jetbrains:kotlin-react-dom:${Versions.kotlinReactDom}"
        const val routerDom = "org.jetbrains:kotlin-react-router-dom:${Versions.kotlinReactRouterDom}"
        const val styled = "org.jetbrains:kotlin-styled:${Versions.kotlinStyled}"
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val test = "io.insert-koin:koin-test:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
        const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    }

    object Redwood {
        const val redwood = "app.cash.redwood:app.cash.redwood.gradle.plugin:${Versions.redwood}"
        const val redwoodBuild = "app.cash.redwood.build:gradle-plugin: ${Versions.redwood}"
    }

    object Log {
        const val slf4j = "org.slf4j:slf4j-simple:${Versions.slf4j}"
        const val kermit = "co.touchlab:kermit:${Versions.kermit}"
    }

    object Glance {
        const val tiles = "androidx.glance:glance-wear-tiles:1.0.0-alpha03"
        const val appwidget = "androidx.glance:glance-appwidget:1.0.0-beta01"
    }

    object Horologist {
        const val composeLayout = "com.google.android.horologist:horologist-compose-layout:${Versions.horologist}"
    }

    const val multiplatformSettings = "com.russhwolf:multiplatform-settings:${Versions.multiplatformSettings}"
}