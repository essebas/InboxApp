package com.zebas2.inboxapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zebas2.inboxapp.domain.usecase.GetMessagesUseCase

class MessagesViewModelFactory(
    private val app: Application,
    private val getMessagesUseCase: GetMessagesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MessagesViewModel(app, getMessagesUseCase) as T
    }

}