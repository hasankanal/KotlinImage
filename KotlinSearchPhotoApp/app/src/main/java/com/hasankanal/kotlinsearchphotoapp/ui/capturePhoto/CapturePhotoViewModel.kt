package com.hasankanal.kotlinsearchphotoapp.ui.capturePhoto

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hasankanal.kotlinsearchphotoapp.data.profile.CaptureDatabase
import com.hasankanal.kotlinsearchphotoapp.data.profile.CapturePhoto
import com.hasankanal.kotlinsearchphotoapp.data.profile.CapturePhotoRepository
import com.hasankanal.kotlinsearchphotoapp.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CapturePhotoViewModel(application: Application) : AndroidViewModel(application), Observable {

    init {
        Log.i("MYTAG", "init")
    }

    val capturePhotosDao = CaptureDatabase.getInstance(application).capturePhotoDao

    val capturePhotoRepository = CapturePhotoRepository(capturePhotosDao)

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    lateinit var savePhotoBitmap : String


    private val _errorToast = MutableLiveData<Boolean>()

    val errorToast : LiveData<Boolean>
        get() = _errorToast

    private val _errorToastUsername = MutableLiveData<Boolean>()

    val errorToastUsername : LiveData<Boolean>
        get() = _errorToastUsername


    private val _errorBitmap = MutableLiveData<Boolean>()

    val errorBitmap : LiveData<Boolean>
        get() = _errorBitmap

    private val _navigateTo = MutableLiveData<Boolean>()

    val navigateTo : LiveData<Boolean>
        get() = _navigateTo



    fun requestPermission(activity: Activity, permissions: Array<String>, requestCode: Int){
        ActivityCompat.requestPermissions(activity, permissions,0)
    }

    fun hasNoPermissions(context: Context): Boolean{
        return ContextCompat.checkSelfPermission(context,
            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context,
            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(context,
            Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
    }

    fun clickedSavePhoto(){
        if(Constants.userName == null && savePhotoBitmap == null){
            _errorToast.value = true
            Log.i("MYTAG","clickedSaveElse")
        }else{
            uiScope.launch {
                val bitmap = savePhotoBitmap
                val currentUser = Constants.userName
                insertImage(CapturePhoto(0,bitmap,currentUser))
                _navigateTo.value = true
                Log.i("MYTAG", "clickedSave")
            }

        }
    }

    private fun insertImage(capturePhoto: CapturePhoto) : Job = viewModelScope.launch{
        capturePhotoRepository.insert(capturePhoto)
    }

    fun doneNavigating(){
        _navigateTo.value = false
        Log.i("MYTAG", "Done navigating ")
    }

    fun doneToast(){
        _errorToast.value = false
        Log.i("MYTAG", "Done taoasting ")
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

}