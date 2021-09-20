package com.hasankanal.kotlinsearchphotoapp.data.profile

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.hasankanal.kotlinsearchphotoapp.data.local.RegisterEntity

@Dao
interface CapturePhotoDao {

    @Query("SELECT * FROM capture_photos")
    fun getAllUsers(): LiveData<List<CapturePhoto>>

    @Insert
    suspend fun insert(capturePhoto: CapturePhoto)


    @Query("SELECT * FROM capture_photos WHERE currentUser LIKE :currentUser")
    fun getUsername(currentUser: String): LiveData<List<CapturePhoto>>

    @Query("DELETE FROM capture_photos")
    suspend fun deleteAll(): Int
}