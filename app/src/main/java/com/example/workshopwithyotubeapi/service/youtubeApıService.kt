package com.example.workshopwithyotubeapi.service

import com.example.workshopwithyotubeapi.model.youtubeModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class youtubeApıService{
    private val BASE_URL ="https://www.googleapis.com/youtube/v3/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(YoutubeAPI::class.java)

    fun getDataService(key:String,part:String,order:String,maxResults:String,regionCode:String,type:String,searchWord:String): Single<youtubeModel> {
        return api.getData(key,part,order,maxResults,regionCode,type,searchWord)
    }


}


/*
https://www.googleapis.com/youtube/v3/search?
key=AIzaSyCmGg37lgh9nEjx7buVRoYIsczvXz43uxM
&channelId=UCmHksmZ3yVMBjEf5faWMmYw
&part=snippet
&order=date
&maxResult=50
&type=video




 */