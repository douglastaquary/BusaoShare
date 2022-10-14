package com.douglastaquary.busaoshare.android

import android.app.Application
import co.touchlab.kermit.Logger
import com.douglastaquary.busaoshare.android.di.appModule
import kotlin.time.ExperimentalTime
import com.douglastaquary.busaoshare.di.initKoin
import org.koin.android.ext.koin.androidContext

@ExperimentalTime
class BusaoShareApplication : Application() {
    override fun onCreate() {
        super.onCreate()


        initKoin {
            androidContext(this@BusaoShareApplication)
            modules(appModule)
        }

        Logger.d { "BusaoShareApplication" }
    }
}