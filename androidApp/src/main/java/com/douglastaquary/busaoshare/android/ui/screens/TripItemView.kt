package com.douglastaquary.busaoshare.android.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.douglastaquary.busaoshare.android.ui.theme.typography
import com.douglastaquary.busaoshare.android.ui.viewModels.SearchTripViewModel
import com.douglastaquary.busaoshare.model.Trip


@Composable
fun TripListView(viewModel: SearchTripViewModel,
                 tripList: List<Trip>,
                 itemClick : (trip : Trip) -> Unit) {
    LazyColumn {
        items(tripList) { trip ->
            TripItemView(trip = trip,
                tripSelected = itemClick,
            )
        }
    }
}

@Composable
fun TripItemView(trip: Trip, tripSelected : (trip : Trip) -> Unit) {
    ProvideTextStyle(typography.subtitle1) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable(onClick = { tripSelected(trip) })
                .padding(horizontal = 16.dp, vertical = 8.dp).fillMaxWidth()) {

            Text(trip.mainTerminal, fontWeight = FontWeight.Bold,
                modifier = Modifier.width(36.dp))

            Text(trip.tripId, maxLines = 1, overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f).padding(start = 16.dp))
        }
        Divider()
    }
}