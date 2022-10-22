package com.douglastaquary.busaoshare.android.ui.state

import com.douglastaquary.busaoshare.model.Trip

data class TripListUiState(
    val items: List<Trip> = emptyList(),
    val isLoading: Boolean = false,
    val userMessage: String? = null
)