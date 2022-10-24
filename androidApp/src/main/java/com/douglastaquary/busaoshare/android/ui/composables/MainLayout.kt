package com.douglastaquary.busaoshare.android.ui

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


@SuppressLint("MissingPermission", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainLayout(viewModel: SearchTripViewModel) {

    val navController = rememberNavController()
    val bottomNavigationItems = listOf(Screens.SearchTripScreen)
    //val bottomBar: @Composable () -> Unit = { BusaoShareBottomNavigation(navController, bottomNavigationItems) }
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    NavHost(navController, startDestination = Screens.SearchTripScreen.route) {
        composable(Screens.SearchTripScreen.route) {
            Column {

                SearchTripScreen(viewModel)
//                TripListScreen(
//                    tripSelected = {},
//                    onSearch = { "Click on search" }
//                )
            }

        }
//        composable(Screens.BusRouteScreen.route+ "/{busId}") { backStackEntry ->
//            BusRouteScreen(viewModel,
//                backStackEntry.arguments?.get("busId") as String,
//                popBack = { navController.popBackStack() })
//        }
    }
}

