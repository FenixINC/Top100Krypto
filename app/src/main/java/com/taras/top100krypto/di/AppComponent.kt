package com.taras.top100krypto.di

import com.taras.top100krypto.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [AppModule::class, RestModule::class, MvpModule::class, ChartModule::class]
)
@Singleton
interface AppComponent {
    fun inject(activity: MainActivity)
}