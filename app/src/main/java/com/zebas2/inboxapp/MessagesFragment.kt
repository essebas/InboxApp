package com.zebas2.inboxapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zebas2.inboxapp.databinding.FragmentMessagesBinding
import com.zebas2.inboxapp.presentation.MainActivity
import com.zebas2.inboxapp.presentation.viewmodel.MessagesViewModel

private const val TAG = "MessagesFragment"

class MessagesFragment : Fragment() {

    private lateinit var viewModel: MessagesViewModel
    private lateinit var binding: FragmentMessagesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_messages, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMessagesBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel

        viewMessagesList()
    }

    fun viewMessagesList() {
        viewModel.getMessages()
        viewModel.messages.observe(viewLifecycleOwner, { response ->
            Log.d(TAG, "viewMessagesList: ${response.toString()}")
            when (response) {
            }
        })
    }

}