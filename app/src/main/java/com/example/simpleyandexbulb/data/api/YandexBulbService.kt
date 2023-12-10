package com.example.simpleyandexbulb.data.api

import com.example.simpleyandexbulb.data.module.BrightnessLevel
import com.example.simpleyandexbulb.data.module.Color
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface YandexBulbService {
    @POST("state/on")
    suspend fun setStateOn(): Response<Boolean>

    @POST("state/off")
    suspend fun setStateOff(): Response<Boolean>

    @GET("state/")
    suspend fun getState(): Response<Boolean>

    @POST("color/")
    suspend fun setColor(@Query("color") color: String): Response<Boolean>

    @GET("/color/names_only")
    suspend fun getColorNames(): Response<List<String>>

    @GET("color/current")
    suspend fun getCurrentColor(): Response<Color>

    @GET("brightness/")
    suspend fun getBrightnessLevels(): Response<BrightnessLevel>

    @POST("brightness/")
    suspend fun setBrightnessLevel(@Query("level") level: Int): Response<Boolean>

    @GET("brightness/current")
    suspend fun getCurrentLevel(): Response<Int>
}