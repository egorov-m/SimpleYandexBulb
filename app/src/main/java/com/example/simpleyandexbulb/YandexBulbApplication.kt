package com.example.simpleyandexbulb

import android.app.Application
import com.example.simpleyandexbulb.di.AppComponent
import com.example.simpleyandexbulb.di.DaggerAppComponent

class YandexBulbApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .create()
    }
}
