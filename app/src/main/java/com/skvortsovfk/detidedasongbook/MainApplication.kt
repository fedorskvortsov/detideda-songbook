package com.skvortsovfk.detidedasongbook

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        if (DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        const val DEBUG = true
    }
}