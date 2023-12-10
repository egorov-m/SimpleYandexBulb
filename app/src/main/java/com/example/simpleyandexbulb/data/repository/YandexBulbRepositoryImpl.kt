package com.example.simpleyandexbulb.data.repository

import com.example.simpleyandexbulb.data.api.YandexBulbService
import com.example.simpleyandexbulb.data.module.BrightnessLevel
import com.example.simpleyandexbulb.data.module.Color
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class YandexBulbRepositoryImpl @Inject constructor(
    private val service: YandexBulbService
) : YandexBulbRepository {

    private suspend fun <T> base(apiFunction: suspend () -> Response<T>): Result<T?> {
        kotlin.runCatching {
            apiFunction.invoke()
        }.fold(
            onSuccess = {
                if (it.isSuccessful) {
                    return Result.success(it.body())
                }
                else {
                    if (it.code() == 400) return Result.success(null)
                    return Result.failure(HttpException(it))
                }
            },
            onFailure = {
                return Result.failure(it)
            }
        )
    }

    override suspend fun setStateOn(): Result<Boolean?> = base { service.setStateOn() }

    override suspend fun setStateOff(): Result<Boolean?> = base { service.setStateOff() }

    override suspend fun getState(): Result<Boolean?> = base { service.getState() }

    override suspend fun setColor(color: String): Result<Boolean?> = base { service.setColor(color) }

    override suspend fun getColorNames(): Result<List<String>?> = base { service.getColorNames() }

    override suspend fun getCurrentColor(): Result<Color?> = base { service.getCurrentColor() }

    override suspend fun getBrightnessLevels(): Result<BrightnessLevel?> = base { service.getBrightnessLevels() }

    override suspend fun setBrightnessLevel(level: Int): Result<Boolean?> = base { service.setBrightnessLevel(level) }

    override suspend fun getCurrentLevel(): Result<Int?> = base { service.getCurrentLevel() }
}
