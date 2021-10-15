package com.zebas2.inboxapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zebas2.inboxapp.domain.usecase.GetUserDetailUseCase

class UserViewModelFactory(
    private val app: Application,
    private val getUserDetailUseCase: GetUserDetailUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(app, getUserDetailUseCase) as T
    }

}