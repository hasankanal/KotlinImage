package com.hasankanal.kotlinsearchphotoapp.data.profile

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [CapturePhoto::class], version = 1, exportSchema = false)
abstract class CaptureDatabase : RoomDatabase() {

    abstract val capturePhotoDao : CapturePhotoDao

    companion object{
        @Volatile
        private var INSTANCE : CaptureDatabase? = null

        fun getInstance(context: Context) : CaptureDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CaptureDatabase::class.java,
                        "capture_photos"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}