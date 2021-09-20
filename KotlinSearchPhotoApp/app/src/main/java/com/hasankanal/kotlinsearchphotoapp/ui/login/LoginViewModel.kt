package com.hasankanal.kotlinsearchphotoapp.ui.login

import android.app.Application
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hasankanal.kotlinsearchphotoapp.data.local.RegisterDatabase
import com.hasankanal.kotlinsearchphotoapp.data.local.RegisterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import com.hasankanal.kotlinsearchphotoapp.utils.Constants

class LoginViewModel(application : Application) : AndroidViewModel(application), Observable {

    private val dao = RegisterDatabase.getInstance(application).registerDatabaseDao

    private val repository = RegisterRepository(dao)

    val users = repository.users

    @Bindable
    val inputUserName = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()

    fun writeUserName(){
        Constants.userName = inputUserName.value.toString()
    }

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _navigateToRegister = MutableLiveData<Boolean>()

    val navigateToRegister : LiveData<Boolean>
        get() = _navigateToRegister

    private val _navigateToProfile = MutableLiveData<Boolean>()

    val navigateToProfile : LiveData<Boolean>
        get() = _navigateToProfile


    private val _errorToast = MutableLiveData<Boolean>()

    val errorToast : LiveData<Boolean>
        get() = _errorToast

    private val _errorToastUserName = MutableLiveData<Boolean>()

    val errorToastUserName : LiveData<Boolean>
        get() = _errorToastUserName


    private val _errorToastInvalidPassword = MutableLiveData<Boolean>()

    val errorToastInvalidPassword : LiveData<Boolean>
        get() = _errorToastInvalidPassword



    fun clickedLoginButton(){
        if(inputUserName.value == null || inputPassword.value == null){
            _errorToast.value = true
        } else {
            uiScope.launch {
                val userNames = repository.getUserName(inputUserName.value!!)
                if(userNames != null){
                    if(userNames.password == inputPassword.value){
                        inputUserName.value == null
                        inputPassword.value == null
                        writeUserName()
                        _navigateToProfile.value = true
                    }else{
                        _errorToastInvalidPassword.value = true
                    }
                } else {
                    _errorToastUserName.value = true
                }
            }
        }
    }

    fun doneNavigatingRegister() {
        _navigateToRegister.value = false
    }

    fun doneNavigatingProfile(){
        _navigateToProfile.value = false
    }

    fun doneToast() {
        _errorToast.value = false
    }


    fun doneToastErrorUsername() {
        _errorToastUserName .value = false

    }

    fun doneToastInvalidPassword() {
        _errorToastInvalidPassword .value = false
    }




    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

}