package com.taras.top100krypto.mvp.contracts

import com.taras.top100krypto.data.model.Currency

class CurrenciesContract {

    interface View : BaseContract.View {
        fun addCurrency(currency: Currency)
        fun notifyAdapter()
        fun showProgress()
        fun hideProgress()
        fun showErrorMessage(error: String?)
        fun refresh()
    }

    abstract class Presenter : BaseContract.Presenter<View>() {
        abstract fun makeList()
        abstract fun refreshList()
    }
}