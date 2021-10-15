package com.zebas2.inboxapp.domain.repository

import com.zebas2.inboxapp.data.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    suspend fun getPosts(): List<Post>?
    suspend fun reloadFromServer(): List<Post>?
    suspend fun getFavoritesPosts(): List<Post>?
    suspend fun savePost(post: Post)
    suspend fun deletePost(post: Post)
    suspend fun updatePost(post: Post)

    //fun getSavedPost(): Flow<List<Post>>

}