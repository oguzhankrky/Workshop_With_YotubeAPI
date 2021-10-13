package com.example.workshopwithyotubeapi.view.Video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.workshopwithyotubeapi.R
import com.example.workshopwithyotubeapi.databinding.ActivityDetailBinding
import com.example.workshopwithyotubeapi.model.Item
import com.squareup.picasso.Picasso
import android.content.Intent

import com.example.workshopwithyotubeapi.view.VideoPlayActivity


class VideoAdapter (private val videoList: List<Item>) :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val inf = LayoutInflater.from(parent.context)
        val binding: ActivityDetailBinding = DataBindingUtil.inflate(inf,R.layout.activity_detail,parent,false )
        return VideoViewHolder(binding)
    }
    override fun getItemCount(): Int {
        return videoList.size
    }
    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videoList[position])
    }

    inner class VideoViewHolder( binding: ActivityDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, VideoPlayActivity::class.java)
                intent.putExtra("videoId",videoList[adapterPosition].id.videoId)//videoList[itemId.toInt()].id.videoId
                binding.root.context.startActivity(intent)
            }
        }
        val videoImage = binding.videoPhoto
        val videoText = binding.videoName
        fun bind(youtubeItem: Item) {
            Picasso.get().load(youtubeItem.snippet.thumbnails.high.url).into(videoImage)
            videoText.text = youtubeItem.snippet.title
        }
    }

}


