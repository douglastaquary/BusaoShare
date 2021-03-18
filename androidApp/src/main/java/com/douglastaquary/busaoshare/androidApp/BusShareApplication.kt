package com.douglastaquary.busaoshare.androidApp

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

import android.app.Application
import android.content.Context

lateinit var appContext: Context

class BusShareApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        appContext = this

        startKoin {
            androidLogger()
            androidContext(this@BusShareApplication)
            modules(appModule)
        }
    }
}