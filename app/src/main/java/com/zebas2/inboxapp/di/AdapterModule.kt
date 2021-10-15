package com.zebas2.inboxapp.di

import com.zebas2.inboxapp.presentation.adapter.MessageAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun provideMessageAdapter(): MessageAdapter {
        return MessageAdapter()
    }

}