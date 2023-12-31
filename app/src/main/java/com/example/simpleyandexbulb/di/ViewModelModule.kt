package com.example.simpleyandexbulb.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simpleyandexbulb.presenter.bulb_control.YandexBulbViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(YandexBulbViewModel::class)
    abstract fun bindSampleViewModel(viewModel: YandexBulbViewModel): ViewModel
}
