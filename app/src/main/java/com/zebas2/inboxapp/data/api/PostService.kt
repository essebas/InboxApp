package com.zebas2.inboxapp.data.api

import com.zebas2.inboxapp.data.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface PostService {

    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

}