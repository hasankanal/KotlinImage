package com.hasankanal.kotlinsearchphotoapp.common

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

abstract class BaseVMFragment <DB : ViewDataBinding, VM : ViewModel> : Fragment() {

    lateinit var dataBinding : DB
    lateinit var viewModel : VM

    @LayoutRes
    abstract fun getLayoutRes() : Int
    abstract fun getViewModel() : Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(getViewModel())
    }
}