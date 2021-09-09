package com.example.workshopwithyotubeapi.service

import com.example.workshopwithyotubeapi.model.youtubeModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Query
//data/2.5/weather?&units=metric&appid=159fdd5b2702c6fce36d677339ab1c40

interface YoutubeAPI {
    @GET("search")
    fun getData(
        @Query("key") key:String,
        @Query("part")  part:String,
        @Query("order") order:String,
        @Query("maxResults") maxResults:String,
        @Query("regionCode") regionCode:String,
        @Query("type") type:String,

    ): Single<youtubeModel>

    /*fun getData(
        @Query("q") cityName:String
    ): Single<youtubeModel>*/
}

/*
https://www.googleapis.com/youtube/v3/search?
key=AIzaSyD9IhAJjjG9xsaNBiiEf8NI69xV7LGfXwk
&part=snippet
&order=date
&maxResult=50
&type=video






*/