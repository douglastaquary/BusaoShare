package com.douglastaquary.busaoshare.android.ui.screens.stops

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.douglastaquary.busaoshare.android.ui.screens.stops.viewModel.StopListViewModel
import com.douglastaquary.busaoshare.android.ui.state.UiState
import com.douglastaquary.busaoshare.model.Stop
import com.douglastaquary.busaoshare.model.Trip
import com.pushpal.jetlime.data.JetLimeItemsModel
import com.pushpal.jetlime.data.config.JetLimeViewConfig
import com.pushpal.jetlime.data.config.LineType
import com.pushpal.jetlime.ui.JetLimeView
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StopListScreen(viewModel: StopListViewModel, tripId: String, popBack: () -> Unit) {

    val stopListState = viewModel.stopListState.collectAsState(UiState.Loading)

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { "${tripId}" },
            navigationIcon = {
                IconButton(onClick = { popBack() }) {
                    //Icon(Icons.Filled.ArrowBack, contentDescription = "Voltar")
                }
            }
        )
    },
        containerColor = Color.Transparent,
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { paddingValues ->
        when (val uiState = stopListState.value) {
            is UiState.Empty -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    //Text(text = "Tente buscar por nome ou número da linha")
                }
            }
            is UiState.Success -> {
                Column(Modifier.padding(paddingValues)) {
                    StopTimelineView(stops = uiState.data, viewModel = viewModel)
                }
            }
            is UiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    //CircularProgressIndicator()
                }

            }
            is UiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    //Text(text = "${uiState.exception}")
                }
            }
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun StopTimelineView(stops: List<Stop>, viewModel: StopListViewModel) {
    val listState = rememberLazyListState()
    val jetItemList: MutableList<JetLimeItemsModel.JetLimeItem> = mutableStateListOf()
    val coroutineScope = rememberCoroutineScope()

    val jetLimeItemsModel by remember {
        derivedStateOf {
            if (stops.isNotEmpty()) {
                var scrollToIndex = 0
                stops.forEach { stop ->
                    jetItemList.add(
                        JetLimeItemsModel.JetLimeItem(
                            title = stop.stopName,
                            description = stop.stopLocationAddress
                        )
                    )
                }
                coroutineScope.launch {
                    listState.scrollToItem(scrollToIndex)
                }
            }
            JetLimeItemsModel(list = jetItemList)
        }
    }
    JetLimeView(
        jetLimeItemsModel = jetLimeItemsModel,
        jetLimeViewConfig = JetLimeViewConfig(lineType = LineType.Solid),
        listState = listState
    )
}
//
//@Composable
//@Preview
//fun StopScreenPreview() {
//
//    val viewModel = StopListViewModel(trip = Trip(id = 1, mainTerminal = "Teste"), )
//StopListScreen(viewModel = StopListViewModel(Trip(id = 1, mainTerminal = "Teste"), trip = ) {
//
//}
//}