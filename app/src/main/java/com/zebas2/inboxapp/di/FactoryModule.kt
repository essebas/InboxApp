package com.zebas2.inboxapp.di

import android.app.Application
import com.zebas2.inboxapp.domain.usecase.GetMessagesUseCase
import com.zebas2.inboxapp.domain.usecase.GetUserDetailUseCase
import com.zebas2.inboxapp.presentation.viewmodel.MessagesViewModelFactory
import com.zebas2.inboxapp.presentation.viewmodel.UserViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideMessagesViewModelFactory(
        application: Application,
        getMessagesUseCase: GetMessagesUseCase
    ): MessagesViewModelFactory {
        return MessagesViewModelFactory(application, getMessagesUseCase)
    }

    @Singleton
    @Provides
    fun provideUserViewModelFactory(
        application: Application,
        getUserDetailUseCase: GetUserDetailUseCase
    ): UserViewModelFactory {
        return UserViewModelFactory(application, getUserDetailUseCase)
    }
}