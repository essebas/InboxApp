package com.zebas2.inboxapp.data.repository.datasource

import com.zebas2.inboxapp.data.model.User
import retrofit2.Response

interface UserRemoteDataSource {

    suspend fun getUserById(id: Int): Response<User>

}