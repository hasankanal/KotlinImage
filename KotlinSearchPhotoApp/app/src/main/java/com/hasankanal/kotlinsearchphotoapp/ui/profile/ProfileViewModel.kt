package com.hasankanal.kotlinsearchphotoapp.ui.profile

import android.app.Application
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hasankanal.kotlinsearchphotoapp.data.profile.CaptureDatabase
import com.hasankanal.kotlinsearchphotoapp.data.profile.CapturePhoto
import com.hasankanal.kotlinsearchphotoapp.data.profile.CapturePhotoRepository
import com.hasankanal.kotlinsearchphotoapp.data.remote.Photos
import com.hasankanal.kotlinsearchphotoapp.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class ProfileViewModel(application : Application) : AndroidViewModel(application), Observable{

    val userName = Constants.userName.toString()

    private val imageList = ArrayList<CapturePhoto>()
    private val liveImages = MutableLiveData<List<CapturePhoto>>()
    val liveData: LiveData<List<CapturePhoto>> get() = liveImages

    private val dao = CaptureDatabase.getInstance(application).capturePhotoDao

    private val repository = CapturePhotoRepository(dao)

    fun getAllImages(): LiveData<List<CapturePhoto>> = repository.photos

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {}


}