package com.zebas2.inboxapp.data.repository.datasource

import com.zebas2.inboxapp.data.model.Post
import retrofit2.Response

interface PostRemoteDataSource {

    suspend fun getPosts(): Response<List<Post>>

    suspend fun deletePostById(id: Int)

}