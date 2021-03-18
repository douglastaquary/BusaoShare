package com.douglastaquary.busaoshare.androidApp

import co.touchlab.kermit.Kermit
import co.touchlab.kermit.LogcatLogger
import com.douglastaquary.busaoshare.shared.SPTransAPIRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { TripListViewModel(get(),get()) }

    single { SPTransAPIRepository() }
    single { Kermit(LogcatLogger()) }
}
