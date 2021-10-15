package com.zebas2.inboxapp.data.repository

import android.util.Log
import com.zebas2.inboxapp.data.model.Post
import com.zebas2.inboxapp.data.repository.datasource.PostLocalDataSource
import com.zebas2.inboxapp.data.repository.datasource.PostRemoteDataSource
import com.zebas2.inboxapp.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

private const val TAG = "PostRepositoryImp"

class PostRepositoryImp(
    private val postRemoteDataSource: PostRemoteDataSource,
    private val postLocalDataSource: PostLocalDataSource
) : PostRepository {

    override suspend fun getPosts(): List<Post>? {
        return getPostsFromDB()
    }

    override suspend fun reloadFromServer(): List<Post>? {
        return getPostsFromAPI()
    }

    override suspend fun getFavoritesPosts(): List<Post>? {
        return getFavoritesPostsFromDB()
    }

    override suspend fun savePost(post: Post) {
        postLocalDataSource.savePost(post)
    }

    override suspend fun deletePost(post: Post) {
        deletePostFromDB(post)
        deletePostFromAPI(post)
    }

    override suspend fun updatePost(post: Post) {
        postLocalDataSource.updatePost(post)
    }

    private suspend fun deletePostFromDB(post: Post) {
        postLocalDataSource.deletePost(post)
    }

    private suspend fun deletePostFromAPI(post: Post) {
        postRemoteDataSource.deletePostById(post.id)
    }

    private suspend fun getFavoritesPostsFromDB(): List<Post> {
        lateinit var postList: List<Post>
        try {
            postList = postLocalDataSource.getFavoritesPost()
        } catch (e: Exception) {
            Log.i(TAG, "getPostsFromAPI: ${e.message.toString()}")
        }
        return postList
    }

    private suspend fun getPostsFromAPI(): List<Post> {
        lateinit var postList: List<Post>
        try {
            val response = postRemoteDataSource.getPosts()
            val body = response.body()
            if (body != null) {
                postList = body
                for (i in 19..postList.size) {
                    postList[i].isRead = true
                }
            }
        } catch (e: Exception) {
            Log.i(TAG, "getPostsFromAPI: ${e.message.toString()}")
        }
        return postList
    }

    private suspend fun getPostsFromDB(): List<Post> {
        lateinit var postList: List<Post>
        try {
            postList = postLocalDataSource.getPosts()
            if (postList.isEmpty()) {
                postList = getPostsFromAPI()
                postLocalDataSource.savePostList(postList)
            }
        } catch (e: Exception) {
            Log.i(TAG, "getPostsFromAPI: ${e.message.toString()}")
        }
        return postList
    }

}