package com.example.workshopwithyotubeapi.view.Video

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workshopwithyotubeapi.R
import com.example.workshopwithyotubeapi.databinding.ActivityDetayBinding
import com.example.workshopwithyotubeapi.databinding.ActivityMainBinding
import com.example.workshopwithyotubeapi.model.Item
import com.example.workshopwithyotubeapi.model.youtubeModel
import com.squareup.picasso.Picasso
import java.util.Collections.list


class VideolarViewHolder(container: ViewGroup) :


    RecyclerView.ViewHolder(

        //xml deki verilere bakarak arayüz viewvi oluşturuyor
        LayoutInflater.from(container.context).inflate
            (
            R.layout.activity_detay,
            container,
            false
        )
    ) {

    val Videoİmage: ImageView = itemView.findViewById(R.id.imgProfilePhoto)
    val Videotext: TextView = itemView.findViewById(R.id.videoName)
    fun bind(youtubeItem: Item) {

        Picasso.get().load(youtubeItem.snippet.thumbnails.high.url).into(Videoİmage)

        Videotext.text =youtubeItem.snippet.title

    }



}