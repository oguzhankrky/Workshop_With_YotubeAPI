package com.example.workshopwithyotubeapi.viewmodel

import android.widget.TextView
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.workshopwithyotubeapi.R
import com.example.workshopwithyotubeapi.databinding.ActivityMainBinding
import com.example.workshopwithyotubeapi.model.youtubeModel
import com.example.workshopwithyotubeapi.service.youtubeApıService
import com.example.workshopwithyotubeapi.view.Video.VideoAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListVideoViewModel: ViewModel() {
    //private lateinit var binding : ActivityMainBinding
    private val youtubeApiService = youtubeApıService()
    private val disposable = CompositeDisposable()
    internal val wmDataKeeper = MutableLiveData<youtubeModel>()

    fun refreshData(searchWord:String) {
        getDataFromAPI(searchWord)
    }

    private fun getDataFromAPI(searchWord:String){

        disposable.add(
            youtubeApiService.getDataService("AIzaSyD9IhAJjjG9xsaNBiiEf8NI69xV7LGfXwk","snippet","date","50","TR","video",searchWord)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<youtubeModel>() {

                    override fun onSuccess(wmData: youtubeModel) {
                        wmDataKeeper.value=wmData


                    }

                    override fun onError(e: Throwable) {


                    }

                })
        )

    }


}