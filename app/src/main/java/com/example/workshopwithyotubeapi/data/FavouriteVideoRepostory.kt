package com.example.myapplication.data

import androidx.lifecycle.LiveData

class FavouriteVideoRepostory(private val favouriteVideoDao: FavouriteVideoDao) {

    val readAllData: LiveData<List<FavouriteVideo>> = favouriteVideoDao.readAllData()

    suspend fun addFavouriteVideo(favouriteVideo :FavouriteVideo)
    {
        favouriteVideoDao.addFavouriteVideo(favouriteVideo)
    }
    fun isRowIsExist(VideoAdress:String): Boolean {
        return favouriteVideoDao.isRowIsExist(VideoAdress)
    }
    suspend fun deleteFavouriteVideo(VideoAddress: String)
    {
        favouriteVideoDao.deleteVideoFromFav( VideoAddress)
    }





}