package com.zebas2.inboxapp.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zebas2.inboxapp.data.model.Post
import com.zebas2.inboxapp.domain.usecase.GetMessagesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "MessagesViewModel"

class MessagesViewModel(
    private val app: Application,
    private val getPostUseCase: GetMessagesUseCase
) : AndroidViewModel(app) {

    val messages: MutableLiveData<List<Post>> = MutableLiveData()

    fun getMessages() = viewModelScope.launch(Dispatchers.IO) {
        try {
            if (isNetWorkAvailable(app)) {
                val apiResult = getPostUseCase.execute()
                messages.postValue(apiResult)
            }
        } catch (e: Exception) {
            Log.i(TAG, "getMessages: ${e.message.toString()}")
        }
    }

    fun isNetWorkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

}