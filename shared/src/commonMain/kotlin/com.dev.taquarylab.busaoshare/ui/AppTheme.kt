package com.dev.taquarylab.busaoshare.ui
//
//
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import com.dev.taquarylab.busaoshare.SharedRes
import dev.icerock.moko.resources.compose.fontFamilyResource

@Composable
fun AppTheme(
    appColorScheme: AppColorScheme = AppTheme.colorScheme,
    content: @Composable () -> Unit
) {
    val fontFamily = fontFamilyResource(SharedRes.fonts.golos.medium)
    MaterialTheme(
        colorScheme = darkColorScheme(),
        typography = typography(fontFamily),
    ) {
        CompositionLocalProvider(
            LocalAppColorScheme provides appColorScheme,
            LocalRippleTheme provides AppRippleTheme
        ) {
            content()
        }
    }
}

internal object AppTheme {

    val colorScheme: AppColorScheme
        @Composable @ReadOnlyComposable get() = LocalAppColorScheme.current
}

private object AppRippleTheme : RippleTheme {

    @Composable override fun defaultColor() = AppTheme.colorScheme.tintedForeground

    @Composable override fun rippleAlpha(): RippleAlpha = DefaultRippleAlpha
}

internal val DefaultRippleAlpha =
    RippleAlpha(
        pressedAlpha = 0.16f,
        focusedAlpha = 0.24f,
        draggedAlpha = 0.24f,
        hoveredAlpha = 0.08f
    )
