package com.zebas2.inboxapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zebas2.inboxapp.domain.usecase.*

class MessagesViewModelFactory(
    private val app: Application,
    private val getMessagesUseCase: GetMessagesUseCase,
    private val saveMessageUseCase: SaveMessageUseCase,
    private val getFavoriteMessagesUseCase: GetFavoriteMessagesUseCase,
    private val deleteSavedMessageUseCase: DeleteSavedMessageUseCase,
    private val updateMessageUseCase: UpdateMessageUseCase,
    private val reloadFromServerUseCase: ReloadFromServerUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MessagesViewModel(
            app,
            getMessagesUseCase,
            saveMessageUseCase,
            getFavoriteMessagesUseCase,
            deleteSavedMessageUseCase,
            updateMessageUseCase,
            reloadFromServerUseCase
        ) as T
    }

}