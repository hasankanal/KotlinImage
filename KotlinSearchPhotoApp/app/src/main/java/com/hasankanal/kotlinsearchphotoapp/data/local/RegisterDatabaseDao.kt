package com.hasankanal.kotlinsearchphotoapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RegisterDatabaseDao {

    @Insert
    suspend fun insert(registerEntity: RegisterEntity)

    @Query("SELECT * FROM New_user_table ORDER BY userId DESC")
    fun getAllUsers(): LiveData<List<RegisterEntity>>

    @Query("DELETE FROM New_user_table")
    suspend fun deleteAll(): Int

    @Query("SELECT * FROM New_user_table WHERE user_name LIKE :userName")
    suspend fun getUsername(userName: String): RegisterEntity?
}