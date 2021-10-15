package com.zebas2.inboxapp.data.repository.datasourceimp

import com.zebas2.inboxapp.data.api.UserService
import com.zebas2.inboxapp.data.model.User
import com.zebas2.inboxapp.data.repository.datasource.UserRemoteDataSource
import retrofit2.Response

class UserRemoteDataSourceImp(
    private val userService: UserService
) : UserRemoteDataSource {

    override suspend fun getUserById(id: Int): Response<User> {
        return userService.getUserById(id)
    }

}