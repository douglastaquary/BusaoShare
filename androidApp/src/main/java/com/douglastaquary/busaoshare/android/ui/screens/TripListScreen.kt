package com.douglastaquary.busaoshare.android.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.datastore.preferences.protobuf.Empty
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import co.touchlab.kermit.Logger
import com.douglastaquary.busaoshare.android.ui.SearchBar
import com.douglastaquary.busaoshare.android.ui.SearchTextField
import com.douglastaquary.busaoshare.android.ui.viewModels.SearchTripViewModel
import com.douglastaquary.busaoshare.android.ui.viewModels.TripListUiState
import com.douglastaquary.busaoshare.android.ui.viewModels.UiState
import com.douglastaquary.busaoshare.model.Trip
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel



@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalLifecycleComposeApi::class
)
@Composable
fun TripListScreen(
    tripSelected: (trip: Trip) -> Unit,
    viewModel: SearchTripViewModel = getViewModel(),
    onSearch: (String?) -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val tripListState = viewModel.tripListState.collectAsState(UiState.Loading)
    val search by viewModel.search.collectAsState()

    Scaffold(
        topBar = {
            SearchBar(
                search = search,
                onSearch = { coroutineScope.launch { viewModel.getTrips("centro") } },
            )
        }
    ) {
        when (val uiState = tripListState.value) {
            is UiState.Empty -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    Text(text = "Tente buscar por nome ou número")
                }
            }
            is UiState.Success -> {
                LazyColumn {
                    if(uiState.data.isEmpty()) {
                        item { EmptyContent() }
                    }
                    items(uiState.data) { trip ->
                        TripItemView(
                            trip = trip,
                            tripSelected = {  trip -> Log.e("TripContent", "Selected: $trip") },
                        )
                    }
                }
            }
            is UiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    CircularProgressIndicator()
                }
            }
            is UiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    Text(text = "${uiState.exception}")
                }
            }
        }
    }
}

@Composable
private fun LazyItemScope.EmptyContent() {
    Box(
        modifier = Modifier.fillParentMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "No trips")
    }
}