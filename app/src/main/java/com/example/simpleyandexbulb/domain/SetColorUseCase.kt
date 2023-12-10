package com.example.simpleyandexbulb.domain

import com.example.simpleyandexbulb.data.repository.YandexBulbRepository
import javax.inject.Inject

interface SetColorUseCase {
    suspend operator fun invoke(color: String): Result<Boolean?>
}

class SetColorUseCaseImpl @Inject constructor(
    private val repository: YandexBulbRepository
) : SetColorUseCase {
    override suspend fun invoke(color: String): Result<Boolean?> = repository.setColor(color)
}