package com.example.simpleyandexbulb.domain

import com.example.simpleyandexbulb.data.repository.YandexBulbRepository
import javax.inject.Inject

interface GetCurrentLevelUseCase {
    suspend operator fun invoke(): Result<Int?>
}

class GetCurrentLevelUseCaseImpl @Inject constructor(
    private val repository: YandexBulbRepository
) : GetCurrentLevelUseCase {
    override suspend fun invoke(): Result<Int?> = repository.getCurrentLevel()
}
