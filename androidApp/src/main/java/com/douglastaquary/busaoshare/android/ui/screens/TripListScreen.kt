package com.douglastaquary.busaoshare.android.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.douglastaquary.busaoshare.android.ui.viewModels.SearchTripViewModel
import com.douglastaquary.busaoshare.android.ui.viewModels.UiState
import com.douglastaquary.busaoshare.model.Trip
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint(
    "CoroutineCreationDuringComposition",
    "UnusedMaterialScaffoldPaddingParameter"
)
@Composable
fun TripListScreen(tripName: String, tripSelected: (trip: Trip) -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    val searchTripViewModel = getViewModel<SearchTripViewModel>()

    val snackbarHostState = remember { SnackbarHostState() }
    val uiState = searchTripViewModel.uiState.collectAsState(UiState.Loading)
    val tripList by searchTripViewModel.tripList.collectAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("BusaoShare") }
            )
        }) { paddingValues ->
        tripList?.let {
            LazyColumn {
                items(tripList) { trip ->
                    TripItemView(trip = trip,
                        tripSelected = tripSelected,
                    )
                }
            }
        }
    }

//    Scaffold(
//        topBar = {
//            TopAppBar(title = { Text("BusaoShare") },
//                actions = {
////                    IconButton(onClick = { viewModel.searchTrip("") }) {
////                        Icon(Icons.Filled.Home, contentDescription = "Center in Eyre Square")
////                    }
//                }
//            )
//        })
//    {
//        Scaffold(
//            topBar = {
//                TopAppBar(
//                    title = { Text("BusaoShare") }
//                )
//            }) { paddingValues ->
//            tripList?.let {
//                LazyColumn {
//                    items(tripList) { trip ->
//                        TripItemView(trip = trip,
//                            tripSelected = itemClick,
//                        )
//                    }
//                }
//            }
//        }
////        if (tripList.isNotEmpty()) {
////            when (val uiState = uiState.value) {
////                is UiState.Success -> {
////                    TripListView(viewModel, uiState.data) {
////
//////                                val firebaseAnalytics = Firebase.analytics
//////                                firebaseAnalytics.logEvent("select_stop") {
//////                                    param("trip_name", it.longName)
//////                                }
////                        coroutineScope.launch {
////                            //sheetState.show()
////                            //viewModel.setCurrentStop(it)
////                        }
////                    }
////                }
////                is UiState.Loading -> {
////                    Box(
////                        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
////                    ) {
////                        CircularProgressIndicator()
////                    }
////                }
////                is UiState.Error -> {
////                    coroutineScope.launch {
////                        snackbarHostState.showSnackbar(
////                            message = "Error retrieving bus stop info"
////                        )
////                    }
////                }
////            }
////        } else {
////            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
////                Box(modifier = Modifier.weight(0.6f)) {
////                    Text("Buscar por nome, número da linha")
////                }
////            }
////        }
//    }
}
