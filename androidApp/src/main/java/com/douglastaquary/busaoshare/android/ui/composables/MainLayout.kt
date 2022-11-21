package com.douglastaquary.busaoshare.android.ui.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.douglastaquary.busaoshare.android.ui.composables.Screens
import com.douglastaquary.busaoshare.android.ui.screens.search.SearchTripScreen
import com.douglastaquary.busaoshare.android.ui.screens.search.viewModels.SearchTripViewModel
import com.douglastaquary.busaoshare.android.ui.screens.stops.StopListScreen
import com.douglastaquary.busaoshare.android.ui.screens.stops.viewModel.StopListViewModel
import com.douglastaquary.busaoshare.model.Trip
import org.koin.androidx.compose.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


@SuppressLint("MissingPermission", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainLayout(viewModel: SearchTripViewModel, stopListViewModel: StopListViewModel) {
    //val stopListViewModel by viewModel<StopListViewModel>()
    val navController = rememberNavController()
    val bottomNavigationItems = listOf(Screens.SearchTripScreen)
    //val bottomBar: @Composable () -> Unit = { BusaoShareBottomNavigation(navController, bottomNavigationItems) }

    NavHost(navController, startDestination = Screens.SearchTripScreen.route) {
        composable(Screens.SearchTripScreen.route) {
            Column {
                SearchTripScreen(viewModel, navController)
            }
        }
        composable(Screens.StopListScreen.route) {
            Column {
                StopListScreen(
                    viewModel = stopListViewModel,
                    tripId = "33934",
                    popBack = { navController.popBackStack() }
                )
            }
        }
    }
}

