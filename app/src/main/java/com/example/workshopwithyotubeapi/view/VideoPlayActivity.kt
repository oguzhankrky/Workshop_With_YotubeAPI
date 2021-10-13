package com.example.workshopwithyotubeapi.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide.init
import com.example.myapplication.data.FavouriteVideo
import com.example.workshopwithyotubeapi.R
import com.example.workshopwithyotubeapi.databinding.ActivityVideoPlayBinding
import com.example.workshopwithyotubeapi.viewmodel.FavouriteVideoViewModel
import com.example.workshopwithyotubeapi.viewmodel.ListVideoViewModel
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import java.util.*
import java.util.regex.Pattern

class VideoPlayActivity : YouTubeBaseActivity()  {
    private lateinit var binding : ActivityVideoPlayBinding
    private lateinit var videolinkV :String
    private  val  mFavouriteVideoViewModel: FavouriteVideoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.getStringExtra("videoId")?.let { videolinkV = it }
        setContentView(R.layout.activity_video_play)
        ActivityVideoPlayBinding()
        getYoutubeVideoIdFromUrlLiveData(videolinkV)
        pushComeBackButton()

        //mFavouriteVideoViewModel= ViewModelProvider(it).get(FavouriteVideoViewModel::class.java)




        binding.addFavouriteButton.setOnClickListener{

            if(videolinkV!=null) {
                if (mFavouriteVideoViewModel.isRowIsExist(videolinkV)) {
                    removeDataToDatabase(videolinkV)
                }
                else
                    insertDataToDatabase(videolinkV)
            }
        }

    }

    private fun pushComeBackButton(){
        binding.ButtonComeBack.setOnClickListener() {
            val intent = Intent(binding.root.context, MainActivity::class.java)
            binding.root.context.startActivity(intent)
        }
    }

    private fun getYoutubeVideoIdFromUrlLiveData(videolinkV:String)
    {
        getYoutubeVideoIdFromUrl("https://www.youtube.com/watch?v=$videolinkV")?.let {
            initilizePlayer(
                it
            )
        }
    }
    private fun insertDataToDatabase(videolinkV:String) {
        if(videolinkV!=null) {
            val favourvideo = FavouriteVideo(0, videolinkV)
            binding.addFavouriteButton.setBackgroundColor(Color.parseColor("#FFE800"))
            mFavouriteVideoViewModel.addfavouriteVideo(favourvideo)
            //Toast.makeText(requireContext(),"Added Fav List ",Toast.LENGTH_LONG).show()
        }
    }
    private fun removeDataToDatabase(videolinkV: String) {
        mFavouriteVideoViewModel.deletefavouriteVideo(videolinkV)
        //Toast.makeText(requireContext(), "Removed Favourite List ", Toast.LENGTH_LONG).show()
        binding.addFavouriteButton.setBackgroundColor(Color.parseColor("#FFFFFF"))
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