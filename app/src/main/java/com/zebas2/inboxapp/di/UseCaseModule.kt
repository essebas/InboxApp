package com.zebas2.inboxapp.di

import com.zebas2.inboxapp.domain.repository.PostRepository
import com.zebas2.inboxapp.domain.repository.UserRepository
import com.zebas2.inboxapp.domain.usecase.GetMessagesUseCase
import com.zebas2.inboxapp.domain.usecase.GetUserDetailUseCase
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

}