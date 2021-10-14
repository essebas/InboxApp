package com.zebas2.inboxapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.zebas2.inboxapp.R
import com.zebas2.inboxapp.data.model.Post
import com.zebas2.inboxapp.domain.usecase.GetMessagesUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var getMessagesUseCase: GetMessagesUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch {
            val list = getMessagesUseCase.execute()
            Log.d(TAG, "onCreate: ${list.toString()}")
        }
    }
}