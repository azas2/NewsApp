package com.mala.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.mala.newsapp.R
import com.mala.newsapp.Repository.Repository
import com.mala.newsapp.ViewModels.NewsViewModel
import com.mala.newsapp.data.DataBase
import com.mala.newsapp.databinding.ActivityNewsBinding


class NewsActivity: AppCompatActivity() {
    lateinit var  viewmodel:NewsViewModel
   private lateinit var binding:ActivityNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentHost) as NavHostFragment
        val navController = navHostFragment.navController

        val newsRepository=Repository(DataBase(this))
        val viewModelProviderFactory=NewsViewModelProvider(newsRepository)
        viewmodel= ViewModelProvider(this,viewModelProviderFactory)[NewsViewModel::class.java]

        binding.bottomNavigationView.setupWithNavController(navController)
    }


}