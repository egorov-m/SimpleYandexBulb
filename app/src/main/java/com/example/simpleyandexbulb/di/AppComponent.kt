package com.example.simpleyandexbulb.di

import com.example.simpleyandexbulb.presenter.bulb_control.BulbControlFragment
import dagger.Component

@Component(
    modules = [ AppModule::class ]
)
interface AppComponent {
    fun inject(fragment: BulbControlFragment)
}
