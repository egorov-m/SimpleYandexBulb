package com.example.simpleyandexbulb.di

import com.example.simpleyandexbulb.data.api.YandexBulbService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideYandexBulbService(): YandexBulbService =
        Retrofit.Builder()
            .baseUrl("http://195.54.14.121:8086/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YandexBulbService::class.java)

}
