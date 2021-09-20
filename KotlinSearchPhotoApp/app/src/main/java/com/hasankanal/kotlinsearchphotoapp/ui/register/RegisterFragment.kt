package com.hasankanal.kotlinsearchphotoapp.ui.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.hasankanal.kotlinsearchphotoapp.R
import com.hasankanal.kotlinsearchphotoapp.common.BaseFragment
import com.hasankanal.kotlinsearchphotoapp.databinding.FragmentRegisterBinding


class RegisterFragment : BaseFragment<FragmentRegisterBinding,RegisterViewModel>(){


    override fun getLayoutRes() : Int = R.layout.fragment_register

    override fun getViewModel() : Class<RegisterViewModel> = RegisterViewModel::class.java

    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
            inflater : LayoutInflater , container : ViewGroup?,
    savedInstanceState:Bundle?
    ):View?{
        super.onCreateView(inflater,container,savedInstanceState)
        dataBinding.registerView=viewModel

        viewModel.navigateTo.observe(viewLifecycleOwner, Observer{  hasFinished->
            if(hasFinished == true){
                Log.i("MYTAG","insidi observe")
                goToLogin()
                viewModel.doneNavigating()
            }
        })

        viewModel.errorToast.observe(viewLifecycleOwner, Observer{   hasError->
            if(hasError == true){
                Toast.makeText(requireContext(),"Lütfen bütün alanları doldurunuz.", Toast.LENGTH_SHORT).show()
                viewModel.doneToast()
            }
        })

        viewModel.errorToastUsername.observe(viewLifecycleOwner, Observer{  hasError->
            if(hasError == true){
                Toast.makeText(requireContext(),"Kullanıcı adı zaten kayıtlı", Toast.LENGTH_SHORT).show()
                viewModel.doneToastUsername()

            }

        })
        return dataBinding.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?){
        super.onViewCreated(view, savedInstanceState)


    }


    private fun goToLogin(){
        Log.i("MYTAG","Action goToLogin")
        val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }






}
