package com.hasankanal.kotlinsearchphotoapp.ui.search


import android.app.Application
import androidx.lifecycle.*
import com.hasankanal.kotlinsearchphotoapp.api.ApiClient
import com.hasankanal.kotlinsearchphotoapp.api.ApiService
import com.hasankanal.kotlinsearchphotoapp.data.remote.Photos
import com.hasankanal.kotlinsearchphotoapp.data.remote.SearchImage
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlin.collections.ArrayList


class SearchViewModel(application: Application) : AndroidViewModel(application){

    private val imageList = ArrayList<Photos>()
    private val liveImages = MutableLiveData<List<Photos>>()
    val liveData: LiveData<List<Photos>> get() = liveImages


    fun searchImages(query: String,randomPage: Int){
        val apiService: ApiService = ApiClient.getClient().create(ApiService::class.java)
        val observable: Observable<SearchImage> = apiService.searchPhotos(query,randomPage,30)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(observable.subscribe{ images ->
            imageList.clear()
            imageList.addAll(images.results)
            liveImages.postValue(imageList)
        })
    }

}