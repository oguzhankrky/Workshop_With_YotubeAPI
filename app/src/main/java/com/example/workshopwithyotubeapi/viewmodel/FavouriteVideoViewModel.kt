package com.example.workshopwithyotubeapi.viewmodel

import android.app.Application
import android.content.res.loader.ResourcesProvider
import android.provider.Settings.Global.getString
import android.provider.Settings.Secure.getString
import android.util.Log
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.FavouriteVideo
import com.example.myapplication.data.FavouriteVideoDao
import com.example.myapplication.data.FavouriteVideoDatabase
import com.example.myapplication.data.FavouriteVideoRepostory
import com.example.workshopwithyotubeapi.R
import com.example.workshopwithyotubeapi.model.youtubeModel
import com.example.workshopwithyotubeapi.service.youtubeApıService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavouriteVideoViewModel(aplication:Application):AndroidViewModel(aplication) {

    var readAllData: LiveData<List<FavouriteVideo>>
    var  repostory:FavouriteVideoRepostory

    init {
        val favouriteVideoDao= FavouriteVideoDatabase.getFavouriteVideoDatabase(aplication)!!.favouriteVideoDao()
        repostory= FavouriteVideoRepostory(favouriteVideoDao)
        readAllData=repostory.readAllData
    }
    fun addfavouriteVideo(favouriteVideo:FavouriteVideo)
    {
        viewModelScope.launch (Dispatchers.IO){
            repostory.addFavouriteVideo(favouriteVideo)
        }
    }
    fun isRowIsExist(VideoAdress:String):Boolean
    {

        return   repostory.isRowIsExist(VideoAdress)

    }
    fun deletefavouriteVideo(VideoAddress: String)
    {
        viewModelScope.launch (Dispatchers.IO){
            repostory.deleteFavouriteVideo(VideoAddress)
        }
    }

    private val youtubeApiService = youtubeApıService()
    val wmDataKeeper: MutableLiveData<youtubeModel?> = MutableLiveData(null)
    fun refreshData(V:String) {
        getDataFromAPI(V)
    }

    private fun getDataFromAPI(V:String){

        youtubeApiService.getDataServiceForFavourite("AIzaSyB4cpn75emuo45iathwp6oN0TzO74k9g2s","snippet","date","50","TR","video",V)
            .enqueue(object: Callback<youtubeModel> {
                override fun onResponse(
                    call: Call<youtubeModel>,
                    response: Response<youtubeModel>
                ) {
                    if(response.isSuccessful && response.body() != null) {
                        wmDataKeeper.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<youtubeModel>, t: Throwable) {
                    if(!t.message.isNullOrEmpty()) Log.e("Error executed", "onFailure: ${t.message}", )
                }

            })


    }


}