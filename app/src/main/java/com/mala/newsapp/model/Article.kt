package com.mala.newsapp.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(
    tableName = "articles"
)

data class Article(
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,
    @SerializedName("id")
    val author: String?=null,
    @SerializedName("myContent")
    val content: String?=null,
    @SerializedName("description")
    val description: String?=null,
    @SerializedName("publishedAt")
    val publishedAt: String?=null,
    @SerializedName("source")
    val source: Source?=null,
    @SerializedName("title")
    val title: String?=null,
    var url: String?=null,
    val urlToImage: String?=null
):Serializable
{
    override fun hashCode(): Int {
        var result = id.hashCode()
        if(url.isNullOrEmpty()){
            result = 31 * result + url.hashCode()
        }
        return result
    }


}