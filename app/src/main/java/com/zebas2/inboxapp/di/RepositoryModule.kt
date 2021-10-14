package com.zebas2.inboxapp.di

import com.zebas2.inboxapp.data.repository.PostRepositoryImp
import com.zebas2.inboxapp.data.repository.datasource.PostRemoteDataSource
import com.zebas2.inboxapp.domain.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providePostRepository(
        remoteDataSource: PostRemoteDataSource
    ): PostRepository {
        return PostRepositoryImp(remoteDataSource)
    }

}