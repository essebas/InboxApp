package com.zebas2.inboxapp.di

import android.app.Application
import com.zebas2.inboxapp.domain.usecase.*
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
        getMessagesUseCase: GetMessagesUseCase,
        saveMessageUseCase: SaveMessageUseCase,
        getFavoriteMessagesUseCase: GetFavoriteMessagesUseCase,
        deleteSavedMessageUseCase: DeleteSavedMessageUseCase,
        updateMessageUseCase: UpdateMessageUseCase,
        reloadFromServerUseCase: ReloadFromServerUseCase
    ): MessagesViewModelFactory {
        return MessagesViewModelFactory(
            application,
            getMessagesUseCase,
            saveMessageUseCase,
            getFavoriteMessagesUseCase,
            deleteSavedMessageUseCase,
            updateMessageUseCase,
            reloadFromServerUseCase
        )
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