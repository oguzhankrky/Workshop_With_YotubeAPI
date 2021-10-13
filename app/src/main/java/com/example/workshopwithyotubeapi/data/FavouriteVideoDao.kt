package com.example.myapplication.data

import androidx.lifecycle.LiveData
import androidx.room.*
import retrofit2.http.DELETE

@Dao
interface FavouriteVideoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addFavouriteVideo(favouritevideo: FavouriteVideo)
    @Query("SELECT * FROM FavouriteVideo ORDER BY id DESC")
    fun readAllData(): LiveData<List<FavouriteVideo>>
    @Query("SELECT EXISTS(SELECT * FROM FavouriteVideo WHERE VideoAddress = :VideoAddress)")
    fun isRowIsExist(VideoAddress : String) : Boolean
    @Query("DELETE FROM FavouriteVideo WHERE VideoAddress = :VideoAddress")
    fun deleteVideoFromFav(VideoAddress: String)



}