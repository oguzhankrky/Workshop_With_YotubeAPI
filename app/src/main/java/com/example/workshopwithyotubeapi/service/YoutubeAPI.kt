package com.example.workshopwithyotubeapi.service

import androidx.annotation.Nullable
import com.example.workshopwithyotubeapi.model.youtubeModel
import io.reactivex.Single
import org.jetbrains.annotations.NotNull
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Query
//data/2.5/weather?&units=metric&appid=159fdd5b2702c6fce36d677339ab1c40

interface YoutubeAPI {
    @GET("search")
    fun getData(
        @Query("key")    @NotNull key:String = "0",
        @Query("part")     @Nullable part:String?,
        @Query("order")    @Nullable order:String?,
        @Query("maxResults")     @Nullable maxResults:String?,
        @Query("regionCode")     @Nullable regionCode:String?,
        @Query("type")     @Nullable type:String?,
        @Query("q")   @NotNull searchWord:String="popular",
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