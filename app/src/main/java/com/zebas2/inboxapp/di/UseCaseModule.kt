package com.zebas2.inboxapp.di

import com.zebas2.inboxapp.domain.repository.PostRepository
import com.zebas2.inboxapp.domain.repository.UserRepository
import com.zebas2.inboxapp.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetMessagesUseCase(
        postRepository: PostRepository
    ): GetMessagesUseCase {
        return GetMessagesUseCase(postRepository)
    }

    @Singleton
    @Provides
    fun provideGetUserDetailUseCase(
        userRepository: UserRepository
    ): GetUserDetailUseCase {
        return GetUserDetailUseCase(userRepository)
    }

    @Singleton
    @Provides
    fun provideSaveMessageUseCase(
        postRepository: PostRepository
    ): SaveMessageUseCase {
        return SaveMessageUseCase(postRepository)
    }

    @Singleton
    @Provides
    fun provideGetFavoriteMessageUseCase(
        postRepository: PostRepository
    ): GetFavoriteMessagesUseCase {
        return GetFavoriteMessagesUseCase(postRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteSavedMessageUseCase(
        postRepository: PostRepository
    ): DeleteSavedMessageUseCase {
        return DeleteSavedMessageUseCase(postRepository)
    }

    @Singleton
    @Provides
    fun provideUpdateMessageUseCase(
        postRepository: PostRepository
    ): UpdateMessageUseCase {
        return UpdateMessageUseCase(postRepository)
    }

    @Singleton
    @Provides
    fun provideReloadFromServerUseCase(
        postRepository: PostRepository
    ): ReloadFromServerUseCase {
        return ReloadFromServerUseCase(postRepository)
    }

}