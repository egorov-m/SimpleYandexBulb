package com.example.simpleyandexbulb.domain

import com.example.simpleyandexbulb.data.repository.YandexBulbRepository
import javax.inject.Inject

interface SetBrightnessLevelUseCase {
    suspend operator fun invoke(level: Int): Result<Boolean?>
}

class SetBrightnessLevelUseCaseImpl @Inject constructor(
    private val repository: YandexBulbRepository
) : SetBrightnessLevelUseCase {
    override suspend fun invoke(level: Int): Result<Boolean?> = repository.setBrightnessLevel(level)
}
