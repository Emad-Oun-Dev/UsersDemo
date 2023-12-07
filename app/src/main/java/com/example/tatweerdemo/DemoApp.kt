package com.example.tatweerdemo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by Emad Mohamed Oun
 * Speedi
 * emad.3oon@gmail.com
 */

@HiltAndroidApp
class DemoApp : Application() {

    companion object {
        lateinit var application: DemoApp
    }

    override fun onCreate() {
        super.onCreate()
        application = this

//        if (Build) {
//            Timber.plant(DebugTree())
//        }
    }
}
