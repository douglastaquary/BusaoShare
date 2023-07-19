package com.dev.taquarylab.busaoshare.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
@ReadOnlyComposable
inline fun Dp.toSp() = with(LocalDensity.current) { this@toSp.toSp() }

inline fun Float.inverseProgress() = 1f - this
