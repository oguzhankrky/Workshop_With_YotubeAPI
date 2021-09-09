package com.example.workshopwithyotubeapi.view

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workshopwithyotubeapi.R
import com.example.workshopwithyotubeapi.databinding.ActivityMainBinding
import com.example.workshopwithyotubeapi.model.youtubeModel
import com.example.workshopwithyotubeapi.service.youtubeApıService

import com.example.workshopwithyotubeapi.view.Video.VideoAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    private val youtubeApiService = youtubeApıService()
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityMainBinding()

        binding.recyclerview1.layoutManager = LinearLayoutManager(this)

        getDataFromAPI()

    }

    /*
    https://www.googleapis.com/youtube/v3/search?
    key=AIzaSyB4cpn75emuo45iathwp6oN0TzO74k9g2s
    &part=snippet
    &order=date
    &maxResult=50
    &type=video

    */
    private fun getDataFromAPI(){

        disposable.add(
            youtubeApiService.getDataService("AIzaSyB4cpn75emuo45iathwp6oN0TzO74k9g2s","snippet","date","50","TR","video")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<youtubeModel>() {

                    override fun onSuccess(wmData: youtubeModel) {

                        binding.recyclerview1.adapter = VideoAdapter(wmData.items)

                    }

                    override fun onError(e: Throwable) {


                    }

                })
        )

    }



    private fun ActivityMainBinding()
    {

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun Context.hideKeyboard(view: View) { //extra added when push button , keyboard is closed.
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}