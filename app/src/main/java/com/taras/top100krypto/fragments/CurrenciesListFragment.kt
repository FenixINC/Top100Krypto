package com.taras.top100krypto.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.taras.top100krypto.data.model.Currency
import com.taras.top100krypto.databinding.FragmentCurrenciesListBinding
import com.taras.top100krypto.di.App
import com.taras.top100krypto.mvp.contracts.CurrenciesContract
import com.taras.top100krypto.mvp.presenter.CurrenciesPresenter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class CurrenciesListFragment : BaseListFragment(), CurrenciesContract.View, Adapter.OnClickListener {
    private lateinit var mBinding: FragmentCurrenciesListBinding

    @Inject
    lateinit var presenter: CurrenciesPresenter

    companion object {
        fun newInstance(): CurrenciesListFragment {
            return CurrenciesListFragment()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = FragmentCurrenciesListBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        App.appComponent.inject(this)
        presenter.attach(this)
        presenter.makeList()
    }

    override fun createAdapterInstance(): Adapter {
        return Adapter(this)
    }

    override fun addCurrency(currency: Currency) {
        viewAdapter.add(currency)
    }

    override fun notifyAdapter() {
        viewAdapter.notifyDataSetChanged()
    }

    override fun showProgress() {
        requireActivity().progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        requireActivity().progress.visibility = View.INVISIBLE
    }

    override fun showErrorMessage(error: String?) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(model: Currency) {

    }

    override fun refresh() {
        viewAdapter.refresh()
    }

    override fun onResume() {
        super.onResume()
        presenter.attach(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.detach()
    }
}
