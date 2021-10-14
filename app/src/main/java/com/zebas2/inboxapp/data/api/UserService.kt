package com.zebas2.inboxapp.data.api

import com.zebas2.inboxapp.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): Response<User>
}