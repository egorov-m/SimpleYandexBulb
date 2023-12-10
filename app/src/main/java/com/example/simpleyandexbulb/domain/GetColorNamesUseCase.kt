package com.example.simpleyandexbulb.domain

import com.example.simpleyandexbulb.data.repository.YandexBulbRepository
import javax.inject.Inject

interface GetColorNamesUseCase {
    suspend operator fun invoke(): Result<List<String>?>
}

class GetColorNamesUseCaseImpl @Inject constructor(
    private val repository: YandexBulbRepository
) : GetColorNamesUseCase {
    override suspend fun invoke(): Result<List<String>?> = repository.getColorNames()
}
