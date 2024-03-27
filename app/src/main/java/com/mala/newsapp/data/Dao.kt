package com.mala.newsapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mala.newsapp.model.Article

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun addArticle(article: Article):Long
    @Query("SELECT * FROM articles")
     fun getAllArticle():LiveData<List<Article>>
    @Delete
    suspend fun deleteArticle(article: Article)
}