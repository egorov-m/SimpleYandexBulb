package com.example.simpleyandexbulb.domain

import com.example.simpleyandexbulb.data.module.BrightnessLevel
import com.example.simpleyandexbulb.data.repository.YandexBulbRepository
import javax.inject.Inject

interface GetBrightnessLevelsUseCase {
    suspend operator fun invoke(): Result<BrightnessLevel?>
}

class GetBrightnessLevelsUseCaseImpl @Inject constructor(
    private val repository: YandexBulbRepository
) : GetBrightnessLevelsUseCase {
    override suspend fun invoke(): Result<BrightnessLevel?> = repository.getBrightnessLevels()
}
