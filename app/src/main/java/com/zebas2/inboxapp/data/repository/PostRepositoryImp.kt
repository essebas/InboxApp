package com.zebas2.inboxapp.data.repository

import android.util.Log
import com.zebas2.inboxapp.data.model.Post
import com.zebas2.inboxapp.data.repository.datasource.PostRemoteDataSource
import com.zebas2.inboxapp.domain.repository.PostRepository

private const val TAG = "PostRepositoryImp"

class PostRepositoryImp(
    private val postRemoteDataSource: PostRemoteDataSource
) : PostRepository {

    override suspend fun getPosts(): List<Post>? {
        return getPostsFromAPI()
    }

    private suspend fun getPostsFromAPI(): List<Post> {
        lateinit var postList: List<Post>
        try {
            val response = postRemoteDataSource.getPosts()
            val body = response.body()
            if (body != null) {
                postList = body
            }
        } catch (e: Exception) {
            Log.i(TAG, "getPostsFromAPI: ${e.message.toString()}")
        }
        return postList
    }

}