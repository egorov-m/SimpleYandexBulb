package com.example.simpleyandexbulb.domain

import com.example.simpleyandexbulb.data.module.Color
import com.example.simpleyandexbulb.data.repository.YandexBulbRepository
import javax.inject.Inject

interface GetCurrentColorUseCase {
    suspend operator fun invoke(): Result<Color?>
}

class GetCurrentColorUseCaseImpl @Inject constructor(
    private val repository: YandexBulbRepository
) : GetCurrentColorUseCase {
    override suspend fun invoke(): Result<Color?> = repository.getCurrentColor()
}
