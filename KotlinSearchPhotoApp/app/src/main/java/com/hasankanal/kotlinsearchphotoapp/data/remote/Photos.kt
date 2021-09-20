package com.hasankanal.kotlinsearchphotoapp.data.remote


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photos(
    val id: String?,
    val created_at: String,
    val description: String,
    val urls: ImageUrls,
    val user: UserDetails
    ) : Parcelable





