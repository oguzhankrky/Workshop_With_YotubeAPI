package com.example.workshopwithyotubeapi.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workshopwithyotubeapi.R
import com.example.workshopwithyotubeapi.databinding.ActivityMainBinding
import com.example.workshopwithyotubeapi.hideKeyboard
import com.example.workshopwithyotubeapi.view.Video.VideoAdapter
import com.example.workshopwithyotubeapi.viewmodel.ListVideoViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    private lateinit var viewModel: ListVideoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityMainBinding()
        viewModel = ViewModelProvider(this).get(ListVideoViewModel::class.java)
        binding.recyclerview1.layoutManager = LinearLayoutManager(this)
        viewModel.getDataFromAPI()
        getLiveData()
        RefreshPage()

        binding.editCityName.hideKeyboard()

    }

    private fun getLiveData(){
        viewModel.wmDataKeeper.observe(this, Observer { data ->
            data?.let {
                binding.recyclerview1.adapter = VideoAdapter(data.items)
            }

        })
    }
    private fun RefreshPage(){
        binding.refreshLayout.setOnRefreshListener {
            viewModel.refreshData()
            binding.refreshLayout.isRefreshing=false
        }
    }

    private fun ActivityMainBinding()
    {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }



}