package com.mala.newsapp.data

import androidx.room.TypeConverter
import com.mala.newsapp.model.Source

class Converters {
    @TypeConverter
    fun fromSource(source: Source):String{
        return source.name
    }
    @TypeConverter
    fun toSource(name:String):Source{
        return Source(name,name)
    }
}