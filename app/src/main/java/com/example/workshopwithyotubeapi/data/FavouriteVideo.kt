package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavouriteVideo")
data class FavouriteVideo (
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val VideoAddress:String
)