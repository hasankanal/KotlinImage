package com.hasankanal.kotlinsearchphotoapp.ui.landing

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.navigation.Navigation

class LandingViewModel (application : Application) : AndroidViewModel(application) {

    fun goToLogin(view : View){
        var action = LandingFragmentDirections.actionLandingFragmentToLoginFragment()
        Navigation.findNavController(view).navigate(action)
    }

    fun goToRegister(view : View){
        var action = LandingFragmentDirections.actionLandingFragmentToRegisterFragment()
        Navigation.findNavController(view).navigate(action)
    }
}