package com.douglastaquary.busaoshare.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.douglastaquary.busaoshare.android.ui.composables.LandingScreen
import com.douglastaquary.busaoshare.android.ui.composables.MainLayout
import com.douglastaquary.busaoshare.android.ui.theme.BusShareTheme
import com.douglastaquary.busaoshare.android.ui.screens.search.viewModels.SearchTripViewModel
import com.douglastaquary.busaoshare.android.ui.screens.stops.viewModel.StopListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class MainActivity : ComponentActivity() {
    private val tripListViewModel by viewModel<SearchTripViewModel>()
    private val stopListViewModel by viewModel<StopListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BusShareTheme {
                var showLandingScreen by remember { mutableStateOf(true) }
                if (showLandingScreen) {
                    LandingScreen(tripListViewModel, onTimeout = { showLandingScreen = false })
                } else {
                    MainLayout(tripListViewModel, stopListViewModel)
                }
            }
        }
    }
}
