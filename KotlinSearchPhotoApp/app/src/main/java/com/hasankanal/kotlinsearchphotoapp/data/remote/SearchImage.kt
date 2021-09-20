package com.hasankanal.kotlinsearchphotoapp.data.remote

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchImage(
    val total: Int,
    val total_pages: Int,
    val results: List<Photos>
) : Parcelable