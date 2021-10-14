package com.zebas2.inboxapp.data.repository

import android.util.Log
import com.zebas2.inboxapp.data.model.User
import com.zebas2.inboxapp.data.repository.datasource.UserRemoteDataSource
import com.zebas2.inboxapp.domain.repository.UserRepository

private const val TAG = "UserRepositoryImp"

class UserRepositoryImp(
    private val userRemoteDataSource: UserRemoteDataSource
) : UserRepository {

    override suspend fun getUserById(id: Int): User? {
        return getUserByIdFromAPI(id)
    }

    private suspend fun getUserByIdFromAPI(id: Int): User {
        lateinit var user: User
        try {
            val response = userRemoteDataSource.getUserById(id)
            val body = response.body()
            if (body != null) {
                user = body
            }
        } catch (e: Exception) {
            Log.i(TAG, "getPostsFromAPI: ${e.message.toString()}")
        }
        return user
    }

}