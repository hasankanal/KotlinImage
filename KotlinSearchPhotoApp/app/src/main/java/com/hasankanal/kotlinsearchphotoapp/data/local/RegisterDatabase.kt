package com.hasankanal.kotlinsearchphotoapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room


@Database(entities = [RegisterEntity::class], version = 1, exportSchema = false)
abstract class RegisterDatabase : RoomDatabase() {

    abstract val registerDatabaseDao : RegisterDatabaseDao

    companion object{

        @Volatile
        private var INSTANCE : RegisterDatabase? = null

        fun getInstance(context: Context) : RegisterDatabase {
            synchronized(this){

                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            RegisterDatabase::class.java,
                            "New_user_table"
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