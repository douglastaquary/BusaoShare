package com.douglastaquary.busaoshare.android.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screens(val route: String, val label: String, val icon: ImageVector? = null) {
    object SearchTripScreen : Screens("TripList", "Buscar", Icons.Default.LocationOn)
    object TripInfoScreen : Screens("TripInfo", "TripInfo")
}
