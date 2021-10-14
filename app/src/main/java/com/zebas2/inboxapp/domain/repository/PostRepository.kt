package com.zebas2.inboxapp.domain.repository

import com.zebas2.inboxapp.data.model.Post

interface PostRepository {

    suspend fun getPosts(): List<Post>?

}