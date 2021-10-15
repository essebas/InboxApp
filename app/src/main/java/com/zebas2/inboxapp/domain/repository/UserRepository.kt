package com.zebas2.inboxapp.domain.repository

import com.zebas2.inboxapp.data.model.User

interface UserRepository {

    suspend fun getUserById(id: Int): User?

}