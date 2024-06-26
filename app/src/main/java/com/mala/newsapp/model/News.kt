package com.mala.newsapp.model

data class News(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)