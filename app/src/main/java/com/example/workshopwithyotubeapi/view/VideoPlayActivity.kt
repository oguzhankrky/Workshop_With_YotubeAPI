package com.example.workshopwithyotubeapi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import com.example.workshopwithyotubeapi.R
import com.example.workshopwithyotubeapi.databinding.ActivityMainBinding
import com.example.workshopwithyotubeapi.databinding.ActivityVideoPlayBinding
import com.example.workshopwithyotubeapi.model.youtubeModel
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import java.util.*
import java.util.regex.Pattern

class VideoPlayActivity : YouTubeBaseActivity() {
    private lateinit var binding : ActivityVideoPlayBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_play)
        ActivityVideoPlayBinding()
        getYoutubeVideoIdFromUrlLiveData()



    }

    private fun getYoutubeVideoIdFromUrlLiveData()
    {
        var videoId= intent.getStringExtra("videoId")
        getYoutubeVideoIdFromUrl("https://www.youtube.com/watch?v=$videoId")?.let {
            initilizePlayer(
                it
            )
        }
    }

    private fun ActivityVideoPlayBinding()
    {

        binding = ActivityVideoPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    private fun initilizePlayer(videoId:String){
        binding.youtubeplayer.initialize(getString(R.string.api_key),object :YouTubePlayer.OnInitializedListener
        {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                if (p1 != null) {
                    p1.loadVideo(videoId)
                }
                if (p1 != null) {
                    p1.play()
                }
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
               Toast.makeText(applicationContext,"error",Toast.LENGTH_LONG).show()
            }

        })
    }




    fun getYoutubeVideoIdFromUrl(inUrl: String): String? {
        if (inUrl.lowercase(Locale.getDefault()).contains("youtu.be"))
        {
            return inUrl.substring(inUrl.lastIndexOf("/")+1)
         }

        val pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*"
        val compiledPattern= Pattern.compile(pattern)
        val matcher = compiledPattern.matcher(inUrl)
        return if (matcher.find()) {
             matcher.group()
        }
        else null
    }



}