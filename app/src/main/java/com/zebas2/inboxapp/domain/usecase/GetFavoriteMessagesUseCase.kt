package com.zebas2.inboxapp.domain.usecase

import com.zebas2.inboxapp.data.model.Post
import com.zebas2.inboxapp.domain.repository.PostRepository


class GetFavoriteMessagesUseCase(private val postRepository: PostRepository) {
    suspend fun execute(): List<Post>? = postRepository.getFavoritesPosts()
}