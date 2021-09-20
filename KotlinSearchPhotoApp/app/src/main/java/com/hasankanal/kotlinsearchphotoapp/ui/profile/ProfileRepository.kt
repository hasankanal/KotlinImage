package com.hasankanal.kotlinsearchphotoapp.ui.profile

import com.hasankanal.kotlinsearchphotoapp.data.profile.CapturePhotoDao
import com.hasankanal.kotlinsearchphotoapp.utils.Constants

class ProfileRepository(private val capturePhotoDao : CapturePhotoDao) {

    var currentUser = Constants.userName
    val images = capturePhotoDao.getUsername(currentUser)
}