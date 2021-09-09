package com.example.workshopwithyotubeapi.view.Video

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workshopwithyotubeapi.model.Item
import com.example.workshopwithyotubeapi.model.youtubeModel
import com.squareup.picasso.Picasso

class VideoAdapter (val Videolarlistesi: List<Item>) :
        RecyclerView.Adapter<VideolarViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideolarViewHolder {
            return VideolarViewHolder(parent)

        }

        override fun getItemCount(): Int {
            return Videolarlistesi.size
        }

        override fun onBindViewHolder(holder: VideolarViewHolder, position: Int) {
            holder.bind(Videolarlistesi[position])

        }
    }
