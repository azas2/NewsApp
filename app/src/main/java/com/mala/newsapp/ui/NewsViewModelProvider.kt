package com.mala.newsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mala.newsapp.Repository.Repository
import com.mala.newsapp.ViewModels.NewsViewModel

class NewsViewModelProvider(
    val newsRepository: Repository
):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository) as T
    }
}