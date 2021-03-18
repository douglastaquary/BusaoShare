package com.douglastaquary.busaoshare.androidApp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.douglastaquary.busaoshare.shared.Trip
import org.koin.androidx.compose.getViewModel

@Composable
fun TripListScreen(searchText: String, popBack: (() -> Unit)?) {

    val tripListViewModel = getViewModel<TripListViewModel>()

    val tripsState = tripListViewModel.tripsUpdated.observeAsState(emptyList())

    tripListViewModel.setTrip(searchText)

    var navigationIcon:  @Composable() (() -> Unit)? = null
    if (popBack != null) { navigationIcon =  {
        IconButton(onClick = { popBack() }) { Icon(Icons.Filled.ArrowBack, contentDescription = "Back") } }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("BShare - $searchText") }, navigationIcon = navigationIcon)
        }){ innerPadding ->
            LazyColumn(contentPadding = innerPadding) {
                items(tripsState.value) { trip ->
                    TripView(trip)
                }
            }
        }

}

@Composable
fun TripView(trip: Trip) {
    Card(elevation = 12.dp, shape = RoundedCornerShape(4.dp), modifier = Modifier.fillMaxWidth()) {

        Row(modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically) {

//            Role.Image(
//                vectorResource(R.drawable.),
//                colorFilter = ColorFilter.tint(if (station.freeBikes() < 5) lowAvailabilityColor else highAvailabilityColor),
//                modifier = Modifier.preferredSize(32.dp),
//                contentDescription = station.freeBikes().toString()
//            )

            Spacer(modifier = Modifier.size(16.dp))

            Column {
                Text(text = trip.firstPartOfTheSign, style = MaterialTheme.typography.h6)

                val textStyle = MaterialTheme.typography.body2
                Row {
                    Text("Free:", style = textStyle, textAlign = TextAlign.Justify, modifier = Modifier.width(48.dp))
                    Text(text = "$trip.travelDestination", style = textStyle)
                }
                Row {
                    Text("Slots:", style = textStyle, textAlign = TextAlign.Justify, modifier = Modifier.width(48.dp), )
                    Text(text = "$$trip.tripId", style = textStyle)
                }
            }
        }
    }
}