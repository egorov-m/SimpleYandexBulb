package com.example.simpleyandexbulb.domain

import com.example.simpleyandexbulb.data.repository.YandexBulbRepository
import javax.inject.Inject

interface SetStateOffUseCase {
    suspend operator fun invoke(): Result<Boolean?>
}

class SetStateOffUseCaseImpl @Inject constructor(
    private val repository: YandexBulbRepository
) : SetStateOffUseCase {
    override suspend fun invoke(): Result<Boolean?> = repository.setStateOff()
}
