package com.example.myapplication.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavouriteVideo::class],version = 3,exportSchema = false)
abstract class FavouriteVideoDatabase:RoomDatabase() {
     abstract fun favouriteVideoDao():FavouriteVideoDao

    companion object {
        private var instance: FavouriteVideoDatabase? = null

        fun getFavouriteVideoDatabase(context: Context): FavouriteVideoDatabase?{
            if(instance != null)
            {
                return instance
            }
            else if (instance == null){
                instance = Room.databaseBuilder(context,
                    FavouriteVideoDatabase::class.java,
                    "FavouriteVideoDatabase.db").allowMainThreadQueries().build()
            }
            return instance
        }



    }

}