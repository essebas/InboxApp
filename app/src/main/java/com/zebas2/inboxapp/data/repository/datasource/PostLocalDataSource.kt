package com.zebas2.inboxapp.data.repository.datasource

import com.zebas2.inboxapp.data.model.Post
import kotlinx.coroutines.flow.Flow

interface PostLocalDataSource {

    suspend fun savePostList(postList: List<Post>)

    suspend fun savePost(post: Post)

    suspend fun deletePost(post: Post)

    suspend fun getPosts(): List<Post>

    suspend fun updatePost(post: Post)

    suspend fun getFavoritesPost(): List<Post>

    //fun getPosts(): Flow<List<Post>>

}