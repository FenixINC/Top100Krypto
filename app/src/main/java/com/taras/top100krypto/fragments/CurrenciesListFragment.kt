package com.taras.top100krypto.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.taras.top100krypto.databinding.FragmentCurrenciesListBinding

class CurrenciesListFragment : Fragment() {

    private lateinit var mBinding: FragmentCurrenciesListBinding

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

    }
}