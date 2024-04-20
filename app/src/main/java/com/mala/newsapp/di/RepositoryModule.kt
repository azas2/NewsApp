package com.mala.newsapp.di

import android.content.Context
import com.mala.newsapp.Repository.Repository
import com.mala.newsapp.data.DataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun getRepository( db:DataBase):Repository{
        return Repository(db)
    }
    @Provides
    fun providDatabase(@ApplicationContext context: Context):DataBase{
        return DataBase.invoke(context)
    }

}