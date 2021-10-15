package com.zebas2.inboxapp.domain.usecase

import com.zebas2.inboxapp.data.model.Post
import com.zebas2.inboxapp.domain.repository.PostRepository

class GetMessagesUseCase(private val postsRepository: PostRepository) {

    suspend fun execute(): List<Post>? = postsRepository.getPosts()

}