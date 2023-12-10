package com.example.simpleyandexbulb.di

import com.example.simpleyandexbulb.data.repository.YandexBulbRepository
import com.example.simpleyandexbulb.data.repository.YandexBulbRepositoryImpl
import com.example.simpleyandexbulb.domain.GetBrightnessLevelsUseCase
import com.example.simpleyandexbulb.domain.GetBrightnessLevelsUseCaseImpl
import com.example.simpleyandexbulb.domain.GetColorNamesUseCase
import com.example.simpleyandexbulb.domain.GetColorNamesUseCaseImpl
import com.example.simpleyandexbulb.domain.GetCurrentColorUseCase
import com.example.simpleyandexbulb.domain.GetCurrentColorUseCaseImpl
import com.example.simpleyandexbulb.domain.GetCurrentLevelUseCase
import com.example.simpleyandexbulb.domain.GetCurrentLevelUseCaseImpl
import com.example.simpleyandexbulb.domain.GetStateUseCase
import com.example.simpleyandexbulb.domain.GetStateUseCaseImpl
import com.example.simpleyandexbulb.domain.SetBrightnessLevelUseCase
import com.example.simpleyandexbulb.domain.SetBrightnessLevelUseCaseImpl
import com.example.simpleyandexbulb.domain.SetColorUseCase
import com.example.simpleyandexbulb.domain.SetColorUseCaseImpl
import com.example.simpleyandexbulb.domain.SetStateOffUseCase
import com.example.simpleyandexbulb.domain.SetStateOffUseCaseImpl
import com.example.simpleyandexbulb.domain.SetStateOnUseCase
import com.example.simpleyandexbulb.domain.SetStateOnUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface AppBindsModule {
    @Binds
    fun bindYandexBulbRepository(repository: YandexBulbRepositoryImpl): YandexBulbRepository

    @Binds
    fun bindGetBrightnessLevelsUseCase(useCaseImpl: GetBrightnessLevelsUseCaseImpl): GetBrightnessLevelsUseCase

    @Binds
    fun bindGetColorNamesUseCase(useCaseImpl: GetColorNamesUseCaseImpl): GetColorNamesUseCase

    @Binds
    fun bindGetCurrentColorUseCase(useCaseImpl: GetCurrentColorUseCaseImpl): GetCurrentColorUseCase

    @Binds
    fun bindGetCurrentLevelUseCase(useCaseImpl: GetCurrentLevelUseCaseImpl): GetCurrentLevelUseCase

    @Binds
    fun bindGetStateUseCase(useCaseImpl: GetStateUseCaseImpl): GetStateUseCase

    @Binds
    fun bindSetBrightnessLevelUseCase(useCaseImpl: SetBrightnessLevelUseCaseImpl): SetBrightnessLevelUseCase

    @Binds
    fun bindSetColorUseCase(useCaseImpl: SetColorUseCaseImpl): SetColorUseCase

    @Binds
    fun bindSetStateOnUseCase(useCaseImpl: SetStateOnUseCaseImpl): SetStateOnUseCase

    @Binds
    fun bindSetStateOffUseCase(useCaseImpl: SetStateOffUseCaseImpl): SetStateOffUseCase
}
