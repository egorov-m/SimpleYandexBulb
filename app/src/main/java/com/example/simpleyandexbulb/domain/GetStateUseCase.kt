package com.example.simpleyandexbulb.domain

import com.example.simpleyandexbulb.data.repository.YandexBulbRepository
import javax.inject.Inject

interface GetStateUseCase {
    suspend operator fun invoke(): Result<Boolean?>
}

class GetStateUseCaseImpl @Inject constructor(
    private val repository: YandexBulbRepository
) : GetStateUseCase {
    override suspend fun invoke(): Result<Boolean?> = repository.getState()
}
