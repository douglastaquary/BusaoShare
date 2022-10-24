package com.douglastaquary.busaoshare.android.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.douglastaquary.busaoshare.android.ui.theme.typography
import com.douglastaquary.busaoshare.model.Trip

@Composable
fun TripItemView(trip: Trip, tripSelected : (trip : Trip) -> Unit) {
        ProvideTextStyle(typography.subtitle1) {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(56.dp)
                    .clickable(onClick = { tripSelected(trip) })
                    .padding(horizontal = 16.dp, vertical = 8.dp, )
                    .fillMaxWidth()) {

                Text(trip.tripId, maxLines = 1)
                Text(trip.mainTerminal, fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp))
            }
            Divider()
        }
}

@Preview
@Composable
fun ComposablePreview() {
    val trip1 = Trip(
        firstPartOfTheSign = "Terminal Bandeira",
        secondaryTerminal = "Terminal jabaquara",
        withoutSecondaryTerminal = false,
        secondPartOfTheSign = 25,
        travelDestination = 23456,
        mainTerminal = "Centro - Brás",
        id = 0,
        tripId = "6006-10"
    )
    TripItemView(trip = trip1, tripSelected = {})
}