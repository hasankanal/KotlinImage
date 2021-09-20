package com.hasankanal.kotlinsearchphotoapp.ui.random

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hasankanal.kotlinsearchphotoapp.api.ApiClient
import com.hasankanal.kotlinsearchphotoapp.api.ApiService
import com.hasankanal.kotlinsearchphotoapp.data.remote.Photos
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RandomViewModel (application : Application) : AndroidViewModel(application) {

    private val imageList = ArrayList<Photos>()
    private val liveImages = MutableLiveData<List<Photos>>()
    val liveData: LiveData<List<Photos>> get() = liveImages



    fun getImageFromApi(randomPage: Int){
        val apiService: ApiService = ApiClient.getClient().create(ApiService::class.java)
        val observable: Observable<List<Photos>> = apiService.getPhotosFromApi(randomPage,30)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(observable.subscribe{ images ->
            imageList.clear()
            imageList.addAll(images)
            liveImages.postValue(imageList)
        })
    }
}