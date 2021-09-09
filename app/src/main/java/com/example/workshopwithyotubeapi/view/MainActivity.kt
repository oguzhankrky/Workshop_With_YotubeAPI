package com.example.workshopwithyotubeapi.view

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workshopwithyotubeapi.R
import com.example.workshopwithyotubeapi.databinding.ActivityMainBinding
import com.example.workshopwithyotubeapi.model.youtubeModel
import com.example.workshopwithyotubeapi.service.youtubeApıService

import com.example.workshopwithyotubeapi.view.Video.VideoAdapter
import com.example.workshopwithyotubeapi.viewmodel.ListVideoViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    private lateinit var viewModel: ListVideoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityMainBinding()

        viewModel = ViewModelProviders.of(this).get(ListVideoViewModel::class.java)
        binding.recyclerview1.layoutManager = LinearLayoutManager(this)

        viewModel.getDataFromAPI()
        getLiveData()
        DoRefresh()



    }

    private fun getLiveData(){
        viewModel.wmDataKeeper.observe(this, Observer { data ->
            data?.let {
                binding.recyclerview1.adapter = VideoAdapter(data.items)
            }

        })
    }
    private fun DoRefresh(){
        binding.refreshlayout.setOnRefreshListener {
            viewModel.refreshData()
            binding.refreshlayout.isRefreshing=false
        }
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