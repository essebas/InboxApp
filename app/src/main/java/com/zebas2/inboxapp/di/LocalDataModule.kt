package com.zebas2.inboxapp.di

import com.zebas2.inboxapp.data.db.DataBase
import com.zebas2.inboxapp.data.db.PostDao
import com.zebas2.inboxapp.data.repository.datasource.PostLocalDataSource
import com.zebas2.inboxapp.data.repository.datasourceimp.PostLocalDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(postDao: PostDao): PostLocalDataSource {
        return PostLocalDataSourceImp(postDao)
    }

}