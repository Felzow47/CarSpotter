package com.felzow47.carspotter

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CarSpotterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: CarSpotterApplication
            private set
    }
}
