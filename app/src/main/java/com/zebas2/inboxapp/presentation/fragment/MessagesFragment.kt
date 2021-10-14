package com.zebas2.inboxapp.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zebas2.inboxapp.R
import com.zebas2.inboxapp.databinding.FragmentMessagesBinding
import com.zebas2.inboxapp.presentation.MainActivity
import com.zebas2.inboxapp.presentation.adapter.MessageAdapter
import com.zebas2.inboxapp.presentation.viewmodel.MessagesViewModel

private const val TAG = "MessagesFragment"

class MessagesFragment : Fragment() {

    private lateinit var viewModel: MessagesViewModel
    private lateinit var binding: FragmentMessagesBinding
    private lateinit var messageAdapter: MessageAdapter

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
        viewModel = (activity as MainActivity).messageViewModel
        messageAdapter = (activity as MainActivity).messageAdapter
        initRecyclerView()
        viewMessagesList()
        setItemClickListener()
    }

    fun viewMessagesList() {
        viewModel.getMessages()
        viewModel.messages.observe(viewLifecycleOwner, { response ->
            Log.d(TAG, "viewMessagesList: ${response.toString()}")
            messageAdapter.submitList(response)
        })
    }

    private fun initRecyclerView() {
        binding.recyclerMessagesList.apply {
            adapter = messageAdapter
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun setItemClickListener() {
        messageAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_message", it)
            }
            var actionNav = R.id.action_messagesFragment_to_messageDetailFragment
            findNavController().navigate(
                actionNav,
                bundle
            )
        }
    }

}