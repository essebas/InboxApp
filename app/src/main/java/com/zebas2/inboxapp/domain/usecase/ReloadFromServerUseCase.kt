package com.zebas2.inboxapp.domain.usecase

import com.zebas2.inboxapp.data.model.Post
import com.zebas2.inboxapp.domain.repository.PostRepository

class ReloadFromServerUseCase(private val repository: PostRepository) {
    suspend fun execute(): List<Post>? = repository.reloadFromServer()
}