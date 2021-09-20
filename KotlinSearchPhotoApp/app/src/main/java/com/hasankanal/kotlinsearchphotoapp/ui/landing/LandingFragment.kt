package com.hasankanal.kotlinsearchphotoapp.ui.landing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.hasankanal.kotlinsearchphotoapp.R
import com.hasankanal.kotlinsearchphotoapp.common.BaseFragment
import com.hasankanal.kotlinsearchphotoapp.databinding.FragmentLandingBinding

class LandingFragment : BaseFragment<FragmentLandingBinding, LandingViewModel>() {

    override fun getLayoutRes(): Int = R.layout.fragment_landing

    override fun getViewModel(): Class<LandingViewModel> = LandingViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        dataBinding.landingView = viewModel
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }




}