package com.hasankanal.kotlinsearchphotoapp.data.profile

import androidx.lifecycle.LiveData

class CapturePhotoRepository(val capturePhotoDao: CapturePhotoDao) {

    var photos = capturePhotoDao.getAllUsers()

    suspend fun insert(capturePhoto: CapturePhoto){
        return capturePhotoDao.insert(capturePhoto)
    }

    fun getPhotosWithCurrentUser(currentUser: String) : LiveData<List<CapturePhoto>> {
        return capturePhotoDao.getUsername(currentUser)
    }


}