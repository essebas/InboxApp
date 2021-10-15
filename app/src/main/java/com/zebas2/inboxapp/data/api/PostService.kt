package com.zebas2.inboxapp.data.api

import com.zebas2.inboxapp.data.model.Post
import com.zebas2.inboxapp.data.model.User
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface PostService {

    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

    @DELETE("posts/{id}")
    suspend fun deletePostById(@Path("id") id: Int)

}