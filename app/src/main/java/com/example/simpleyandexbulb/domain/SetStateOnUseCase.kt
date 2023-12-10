package com.example.simpleyandexbulb.domain

import com.example.simpleyandexbulb.data.repository.YandexBulbRepository
import javax.inject.Inject

interface SetStateOnUseCase {
    suspend operator fun invoke(): Result<Boolean?>
}

class SetStateOnUseCaseImpl @Inject constructor(
    private val repository: YandexBulbRepository
) : SetStateOnUseCase {
    override suspend fun invoke(): Result<Boolean?> = repository.setStateOn()
}
