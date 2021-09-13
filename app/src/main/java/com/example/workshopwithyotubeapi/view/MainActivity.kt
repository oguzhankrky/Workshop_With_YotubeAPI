package com.example.workshopwithyotubeapi.view


import android.content.SharedPreferences
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
    private lateinit var GET: SharedPreferences
    private lateinit var SET: SharedPreferences.Editor
    private lateinit var viewModel: ListVideoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GET = getSharedPreferences(packageName, MODE_PRIVATE)
        SET = GET.edit()

        ActivityMainBinding()
        firstInitialviewModel()
        getLiveData()
        RefreshPage()
        whenPushSearchButton()

    }
    fun firstInitialviewModel(){

        viewModel = ViewModelProvider(this).get(ListVideoViewModel::class.java)
        binding.recyclerview1.layoutManager = LinearLayoutManager(this)
        var SearchWord=GET.getString("q","Popular videos in turkey")
        binding.editVideoName.setText(SearchWord)
        if(SearchWord!=null) {
            viewModel.refreshData(SearchWord)
        }

    }

    private fun whenPushSearchButton()
    {
        binding.searchButton.setOnClickListener{
            var editVideoName = binding.editVideoName.text.toString()
            SET.putString("q",editVideoName)
            SET.apply()
            binding.editVideoName.hideKeyboard() //For close keyboard after searching.
            if(editVideoName!=null)
                viewModel.refreshData(editVideoName)
            getLiveData()
        }
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
            var SearchWord=GET.getString("q","Popular videos in turkey")
            binding.editVideoName.setText(SearchWord)
            if(SearchWord!=null) {
                viewModel.refreshData(SearchWord)
            }


            binding.refreshLayout.isRefreshing=false
        }
    }

    private fun ActivityMainBinding()
    {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }



}