package com.taras.top100krypto.mvp.presenter

import com.taras.top100krypto.data.model.Currency
import com.taras.top100krypto.data.service.CoinGeckoService
import com.taras.top100krypto.di.App
import com.taras.top100krypto.formatThousands
import com.taras.top100krypto.mvp.contracts.CurrenciesContract
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CurrenciesPresenter : CurrenciesContract.Presenter() {

    @Inject
    lateinit var geckoApi: CoinGeckoService

    //инициализируем компоненты Даггера
    init {
        App.appComponent.inject(this)
    }

    //создаем список, загружая данные с помощью RxJava
    override fun makeList() {
        view.showProgress()

        //подписываемся на поток данных
        subscribe(geckoApi.getCoinMarket()

                //определяем отдельный поток для отправки данных
                .subscribeOn(Schedulers.io())

                //получаем данные в основном потоке
                .observeOn(AndroidSchedulers.mainThread())

                //преобразуем List<GeckoCoin> в Observable<GeckoCoin>
                .flatMap { Observable.fromIterable(it) }

                //наполняем поля элемента списка для адаптера
                .doOnNext {
                    view.addCurrency(
                            Currency(
                                    it.id,
                                    it.symbol,
                                    it.name,
                                    it.image,
                                    it.current_price,
                                    it.market_cap.formatThousands(),
                                    it.market_cap_rank,
                                    it.total_volume,
                                    it.price_change_percentage_24h,
                                    it.market_cap_change_percentage_24h,
                                    it.circulating_supply,
                                    it.total_supply,
                                    it.ath,
                                    it.ath_change_percentage
                            )
                    )
                }

                //вызывается при вызове onComplete
                .doOnComplete {
                    view.hideProgress()
                }

                //подписывает Observer на Observable
                .subscribe({
                    view.hideProgress()
                    view.notifyAdapter()
                }, {
                    view.showErrorMessage(it.message)
                    view.hideProgress()
                    it.printStackTrace()
                })
        )
    }


    //обновляем список
    override fun refreshList() {
        view.refresh()
        makeList()
    }
}