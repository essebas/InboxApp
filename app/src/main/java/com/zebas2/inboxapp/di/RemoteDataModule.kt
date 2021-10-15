package com.zebas2.inboxapp.di

import com.zebas2.inboxapp.data.api.PostService
import com.zebas2.inboxapp.data.api.UserService
import com.zebas2.inboxapp.data.repository.datasource.PostRemoteDataSource
import com.zebas2.inboxapp.data.repository.datasource.UserRemoteDataSource
import com.zebas2.inboxapp.data.repository.datasourceimp.PostRemoteDataSourceImp
import com.zebas2.inboxapp.data.repository.datasourceimp.UserRemoteDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun providePostRemoteDataSource(
        postService: PostService
    ): PostRemoteDataSource {
        return PostRemoteDataSourceImp(postService)
    }

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(
        userService: UserService
    ): UserRemoteDataSource {
        return UserRemoteDataSourceImp(userService)
    }

}