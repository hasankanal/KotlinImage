package com.hasankanal.kotlinsearchphotoapp.ui.register

import android.app.Application
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hasankanal.kotlinsearchphotoapp.data.local.RegisterDatabase
import com.hasankanal.kotlinsearchphotoapp.data.local.RegisterEntity
import com.hasankanal.kotlinsearchphotoapp.data.local.RegisterRepository
import kotlinx.coroutines.*

class RegisterViewModel(application: Application) : AndroidViewModel(application), Observable{

    init {
        Log.i("MYTAG", "init")
    }

    val dao = RegisterDatabase.getInstance(application).registerDatabaseDao

    val repository = RegisterRepository(dao)

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    @Bindable
    val inputFirstName = MutableLiveData<String>()

    @Bindable
    val inputLastName = MutableLiveData<String>()

    @Bindable
    val inputUserName = MutableLiveData<String>()

    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()

    private val _errorToast = MutableLiveData<Boolean>()

    val errorToast : LiveData<Boolean>
        get() = _errorToast

    private val _errorToastUsername = MutableLiveData<Boolean>()

    val errorToastUsername : LiveData<Boolean>
        get() = _errorToastUsername

    private val _navigateTo = MutableLiveData<Boolean>()

    val navigateTo : LiveData<Boolean>
        get() = _navigateTo

    fun clickedRegisterButton(){
        Log.i("MYTAG", "Inside SUBMIT BUTTON")
        if(inputFirstName.value == null || inputLastName == null || inputUserName.value == null || inputEmail.value == null || inputPassword.value == null){
            _errorToast.value = true
        }else{
            uiScope.launch {
                    val userNames = repository.getUserName(inputUserName.value!!)
                    Log.i("MYTAG", userNames.toString() + "------------------")
                    if(userNames != null){
                        _errorToastUsername.value = true
                        Log.i("MYTAG", "Inside if Not null")
                    }else{
                        Log.i("MYTAG", "OK im in")
                        val firstName = inputFirstName.value!!
                        val lastName = inputLastName.value!!
                        val userName = inputUserName.value!!
                        val email = inputEmail.value!!
                        val password = inputPassword.value!!
                        Log.i("MYTAG", "insidi Sumbit")
                        insertUser(RegisterEntity(0, firstName, lastName, userName, email, password))
                        inputFirstName.value = null
                        inputLastName.value = null
                        inputUserName.value = null
                        inputEmail.value = null
                        inputPassword.value = null
                        _navigateTo.value = true
                    }
            }
        }
    }


    fun doneNavigating(){
        _navigateTo.value = false
        Log.i("MYTAG", "Done navigating ")
    }

    fun doneToast(){
        _errorToast.value = false
        Log.i("MYTAG", "Done taoasting ")
    }

    fun doneToastUsername(){
        _errorToastUsername.value = false
        Log.i("MYTAG", "Done taoasting  username")
    }



    private fun insertUser(user : RegisterEntity) : Job = viewModelScope.launch {
        repository.insert(user)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }





}