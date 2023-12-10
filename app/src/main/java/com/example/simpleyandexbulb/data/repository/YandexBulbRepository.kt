package com.example.simpleyandexbulb.data.repository

import com.example.simpleyandexbulb.data.module.BrightnessLevel
import com.example.simpleyandexbulb.data.module.Color

interface YandexBulbRepository {
    suspend fun setStateOn(): Result<Boolean?>
    suspend fun setStateOff(): Result<Boolean?>
    suspend fun getState(): Result<Boolean?>
    suspend fun setColor(color: String): Result<Boolean?>
    suspend fun getColorNames(): Result<List<String>?>
    suspend fun getCurrentColor(): Result<Color?>
    suspend fun getBrightnessLevels(): Result<BrightnessLevel?>
    suspend fun setBrightnessLevel(level: Int): Result<Boolean?>
    suspend fun getCurrentLevel(): Result<Int?>
}