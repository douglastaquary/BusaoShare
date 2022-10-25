package com.douglastaquary.busaoshare.android.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.douglastaquary.busaoshare.android.ui.screens.search.viewModels.SearchTripViewModel
import kotlinx.coroutines.delay

private const val SplashWaitTime: Long = 1000

@Composable
fun LandingScreen(viewModel: SearchTripViewModel, modifier: Modifier = Modifier, onTimeout: () -> Unit) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val currentOnTimeout by rememberUpdatedState(onTimeout)

        LaunchedEffect(true) {
            delay(SplashWaitTime)
            currentOnTimeout()
        }

        //Image(painterResource(id = R.drawable.ic_bus), contentDescription = null)
    }
}