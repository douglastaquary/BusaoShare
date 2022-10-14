package com.douglastaquary.busaoshare.android

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.douglastaquary.busaoshare.android.ui.screens.LandingScreen
import com.douglastaquary.busaoshare.android.ui.screens.Screens
import com.douglastaquary.busaoshare.android.ui.screens.SearchView
import com.douglastaquary.busaoshare.android.ui.screens.TripListScreen
import com.douglastaquary.busaoshare.android.ui.theme.BusShareTheme
import com.douglastaquary.busaoshare.android.ui.viewModels.SearchTripViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class MainActivity : ComponentActivity() {
    private val tripListViewModel by viewModel<SearchTripViewModel>()


    @OptIn(ExperimentalComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BusShareTheme {
                var showLandingScreen by remember { mutableStateOf(true) }
                if (showLandingScreen) {
                    LandingScreen(tripListViewModel, onTimeout = { showLandingScreen = false })
                } else {
                    MainLayout(tripListViewModel)
                }
            }
        }
    }
}

@SuppressLint("MissingPermission", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainLayout(viewModel: SearchTripViewModel) {

    val navController = rememberNavController()
    val bottomNavigationItems = listOf(Screens.SearchTripScreen)
    val bottomBar: @Composable () -> Unit = { BusaoShareBottomNavigation(navController, bottomNavigationItems) }
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    NavHost(navController, startDestination = Screens.SearchTripScreen.route) {
        composable(Screens.SearchTripScreen.route) {
            Column {
                TripListScreen(
                    tripSelected = {},
                    onSearch = { "Click on search" }
                )
            }

        }
//        composable(Screens.BusRouteScreen.route+ "/{busId}") { backStackEntry ->
//            BusRouteScreen(viewModel,
//                backStackEntry.arguments?.get("busId") as String,
//                popBack = { navController.popBackStack() })
//        }
    }
}


@Composable
private fun BusaoShareBottomNavigation(navController: NavHostController, items: List<Screens>) {

    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route


        items.forEach { screen ->
            BottomNavigationItem(
                icon = { screen.icon?.let { Icon(it, contentDescription = screen.label) } },
                label = { Text(screen.label) },
                selected = currentRoute == screen.route,
                alwaysShowLabel = false, // This hides the title for the unselected items
                onClick = {
                    // This if check gives us a "singleTop" behavior where we do not create a
                    // second instance of the composable if we are already on that destination
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
                    }
                }
            )
        }
    }
}
