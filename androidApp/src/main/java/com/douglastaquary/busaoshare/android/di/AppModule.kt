package com.douglastaquary.busaoshare.android.di

import com.douglastaquary.busaoshare.android.ui.screens.search.viewModels.SearchTripViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val busaoShareAppModule = module {
    viewModel { SearchTripViewModel(get()) }
}

// Gather all app modules
val appModule = listOf(busaoShareAppModule)