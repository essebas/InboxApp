package com.zebas2.inboxapp.data.repository.datasourceimp

import com.zebas2.inboxapp.data.api.PostService
import com.zebas2.inboxapp.data.model.Post
import com.zebas2.inboxapp.data.repository.datasource.PostRemoteDataSource
import retrofit2.Response

class PostRemoteDataSourceImp(
    private val postService: PostService
) : PostRemoteDataSource {

    override suspend fun getPosts(): Response<List<Post>> {
        return postService.getPosts()
    }

}