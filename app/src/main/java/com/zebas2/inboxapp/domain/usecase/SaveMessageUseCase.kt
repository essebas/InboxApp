package com.zebas2.inboxapp.domain.usecase

import com.zebas2.inboxapp.data.model.Post
import com.zebas2.inboxapp.domain.repository.PostRepository

class SaveMessageUseCase(private val postRepository: PostRepository) {
    suspend fun execute(post: Post) = postRepository.savePost(post)
}