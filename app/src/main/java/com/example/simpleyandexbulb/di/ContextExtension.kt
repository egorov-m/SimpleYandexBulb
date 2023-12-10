package com.example.simpleyandexbulb.di

import android.content.Context
import com.example.simpleyandexbulb.YandexBulbApplication


val Context.appComponent: AppComponent
    get() = when(this) {
        is YandexBulbApplication -> appComponent
        else -> applicationContext.appComponent
    }
