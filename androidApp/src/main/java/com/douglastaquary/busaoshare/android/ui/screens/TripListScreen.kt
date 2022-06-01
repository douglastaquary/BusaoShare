package com.douglastaquary.busaoshare.android.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.douglastaquary.busaoshare.android.ui.viewModels.SearchTripViewModel
import com.douglastaquary.busaoshare.android.ui.viewModels.UiState
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint(
    "CoroutineCreationDuringComposition",
    "UnusedMaterialScaffoldPaddingParameter"
)
@Composable
fun TripListScreen(viewModel: SearchTripViewModel, navController: NavHostController) {
    val coroutineScope = rememberCoroutineScope()

    val snackbarHostState = remember { SnackbarHostState() }
    val tripListState = viewModel.tripSearchListState.collectAsState(UiState.Loading)
    val tripList by viewModel.tripList.collectAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("BusaoShare") },
                actions = {
                    IconButton(onClick = { "Click HOME" }) {
                        Icon(Icons.Filled.Home, contentDescription = "Center in Eyre Square")
                    }
                }
            )
        })
    {
        if (tripList.isNotEmpty()) {
            when (val uiState = tripListState.value) {
                is UiState.Success -> {
                    TripListView(viewModel, uiState.data) {

//                                val firebaseAnalytics = Firebase.analytics
//                                firebaseAnalytics.logEvent("select_stop") {
//                                    param("trip_name", it.longName)
//                                }
                        coroutineScope.launch {
                            //sheetState.show()
                            //viewModel.setCurrentStop(it)
                        }
                    }
                }
                is UiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center)
                    ) {
                        CircularProgressIndicator()
                    }
                }
                is UiState.Error -> {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Error retrieving bus stop info"
                        )
                    }
                }
            }
        } else {
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Box(modifier = Modifier.weight(0.6f)) {
                    Text("Buscar por nome, número da linha")
                }
            }
        }
    }
}
