package com.hasankanal.kotlinsearchphotoapp.data.profile


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "capture_photos")
data class CapturePhoto (
    @PrimaryKey
    val uid: Int,


    @ColumnInfo(name = "photo")
    val savePhoto: String?,


    @ColumnInfo(name = "currentUser")
    val currentUser: String?
        )