package com.example.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
abstract class ProductDataBase: RoomDatabase(){
    abstract val productDao: Dao
    companion object{
        @Volatile
        private var INSTANCE: ProductDataBase? = null

        fun getInstance(context: Context):ProductDataBase{
            synchronized(this) {
                var instance = INSTANCE
                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ProductDataBase::class.java,
                        "sleep_history_database"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
                    INSTANCE = instance
                }
                // Return instance; smart cast to be non-null.
                return instance
            }
        }
    }
}