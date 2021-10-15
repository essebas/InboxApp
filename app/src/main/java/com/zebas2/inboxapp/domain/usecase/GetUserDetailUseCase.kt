package com.zebas2.inboxapp.domain.usecase

import com.zebas2.inboxapp.data.model.User
import com.zebas2.inboxapp.domain.repository.UserRepository

class GetUserDetailUseCase(private val userRepository: UserRepository) {

    suspend fun execute(id: Int): User? = userRepository.getUserById(id)

}