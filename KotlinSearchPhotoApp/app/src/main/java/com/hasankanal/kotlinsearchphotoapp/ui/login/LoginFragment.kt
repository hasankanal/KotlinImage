package com.hasankanal.kotlinsearchphotoapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.hasankanal.kotlinsearchphotoapp.R
import com.hasankanal.kotlinsearchphotoapp.common.BaseFragment
import com.hasankanal.kotlinsearchphotoapp.databinding.FragmentLoginBinding


class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override fun getLayoutRes(): Int = R.layout.fragment_login

    override fun getViewModel(): Class<LoginViewModel> = LoginViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       super.onCreateView(inflater, container, savedInstanceState)
        dataBinding.loginView = viewModel
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.errorToast.observe(viewLifecycleOwner, Observer { hasError ->
            if(hasError == true){
                Toast.makeText(requireContext(),"Lütfen bütün alanları doldurunuz.", Toast.LENGTH_SHORT).show()
                viewModel.doneToast()
            }
        })

        viewModel.errorToastUserName.observe(viewLifecycleOwner, Observer { hasError ->
            if(hasError == true ){
                Toast.makeText(requireContext(), "Kullanıcı adı bulunamadı.", Toast.LENGTH_SHORT).show()
                viewModel.doneToastErrorUsername()
            }
        })

        viewModel.errorToastInvalidPassword.observe(viewLifecycleOwner, Observer {  hasError ->
            if(hasError == true){
                Toast.makeText(requireContext(), "Şifre hatalı. Lütfen tekrar deneyin.", Toast.LENGTH_SHORT).show()
                viewModel.doneToastInvalidPassword()
            }
        })

        viewModel.navigateToProfile.observe(viewLifecycleOwner, Observer { hasFinished ->
            if(hasFinished == true){
                goToProfile()
                viewModel.doneNavigatingProfile()
            }
        })

        viewModel.navigateToRegister.observe(viewLifecycleOwner, Observer { toRegister ->
            if(toRegister == true){
                goToRegister()
                viewModel.doneNavigatingRegister()
            }
        })
    }

    private fun goToProfile(){
        var action = LoginFragmentDirections.actionLoginFragmentToProfileFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

    private fun goToRegister(){
        var action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }
}