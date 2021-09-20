package com.hasankanal.kotlinsearchphotoapp.data.remote


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserDetails (
    val id: String,
    val username: String,
    val name: String,
    val portfolio_url: String?,
    val bio: String?,
    val location: String?,
    val total_likes: Int,
    val total_photos: Int,
    val total_collection: Int,
        ) : Parcelable