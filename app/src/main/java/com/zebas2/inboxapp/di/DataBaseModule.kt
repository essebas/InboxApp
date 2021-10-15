package com.zebas2.inboxapp.di

import android.app.Application
import androidx.room.Room
import com.zebas2.inboxapp.data.db.DataBase
import com.zebas2.inboxapp.data.db.PostDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Singleton
    @Provides
    fun provideDataBase(app: Application): DataBase {
        return Room.databaseBuilder(app, DataBase::class.java, "inbox_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePostDao(dataBase: DataBase): PostDao {
        return dataBase.getPostDao()
    }

}