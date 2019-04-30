package com.taras.top100krypto.di

import com.taras.top100krypto.MainActivity
import com.taras.top100krypto.fragments.CurrenciesListFragment
import com.taras.top100krypto.mvp.presenter.CurrenciesPresenter
import com.taras.top100krypto.mvp.presenter.LatestChartPresenter
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [AppModule::class, RestModule::class, MvpModule::class, ChartModule::class]
)
@Singleton
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(presenter: CurrenciesPresenter)
    fun inject(presenter: LatestChartPresenter)
    fun inject(fragment: CurrenciesListFragment)
}