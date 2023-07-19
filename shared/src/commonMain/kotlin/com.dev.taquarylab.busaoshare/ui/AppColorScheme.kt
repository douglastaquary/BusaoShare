package com.dev.taquarylab.busaoshare.ui


import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color

private val darkYellowGreen = Color(0xFF1A1E00)
private val darkYellowGreenLight = Color(0xFF2E3300)
private val vibrantYellowGreen = Color(0xFFBFD100)
private val darkYellow = Color(0xFF0E0E0A)
private val darkGrayishYellow = Color(0xFF20201B)

@Stable
class AppColorScheme(
    tintedBackground: Color = darkYellowGreen,
    tintedSurface: Color = darkYellowGreenLight,
    tintedForeground: Color = vibrantYellowGreen,
    surfaceContainer: Color = darkGrayishYellow,
    surfaceContainerLowest: Color = darkYellow,
    textEmphasisHigh: Color = Color.White.copy(alpha = 0.9f),
    textEmphasisMed: Color = Color.White.copy(alpha = 0.7f)
) {

    var tintedBackground by mutableStateOf(tintedBackground, structuralEqualityPolicy())
        internal set

    var tintedSurface by mutableStateOf(tintedSurface, structuralEqualityPolicy())
        internal set

    var tintedForeground by mutableStateOf(tintedForeground, structuralEqualityPolicy())
        internal set

    var surfaceContainer by mutableStateOf(surfaceContainer, structuralEqualityPolicy())
        internal set

    var surfaceContainerLowest by mutableStateOf(surfaceContainerLowest, structuralEqualityPolicy())
        internal set

    var textEmphasisHigh by mutableStateOf(textEmphasisHigh, structuralEqualityPolicy())
        internal set

    var textEmphasisMed by mutableStateOf(textEmphasisMed, structuralEqualityPolicy())
        internal set

    fun copy(
        tintedBackground: Color = this.tintedBackground,
        tintedSurface: Color = this.surfaceContainer,
        tintedForeground: Color = this.tintedForeground,
        surfaceContainer: Color = this.surfaceContainer,
        surfaceContainerLowest: Color = this.surfaceContainerLowest,
        textEmphasisHigh: Color = this.textEmphasisHigh,
        textEmphasisMed: Color = this.textEmphasisMed
    ): AppColorScheme =
        AppColorScheme(
            tintedBackground = tintedBackground,
            tintedSurface = tintedSurface,
            tintedForeground = tintedForeground,
            surfaceContainer = surfaceContainer,
            surfaceContainerLowest = surfaceContainerLowest,
            textEmphasisHigh = textEmphasisHigh,
            textEmphasisMed = textEmphasisMed
        )
}

internal val LocalAppColorScheme = staticCompositionLocalOf { AppColorScheme() }
