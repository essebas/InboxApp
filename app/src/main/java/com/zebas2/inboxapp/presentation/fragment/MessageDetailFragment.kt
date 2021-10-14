package com.zebas2.inboxapp.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zebas2.inboxapp.R
import androidx.navigation.fragment.navArgs
import com.zebas2.inboxapp.databinding.FragmentMessageDetailBinding
import com.zebas2.inboxapp.presentation.MainActivity
import com.zebas2.inboxapp.presentation.viewmodel.UserViewModel

private const val TAG = "MessageDetailFragment"

class MessageDetailFragment : Fragment() {

    private lateinit var binding: FragmentMessageDetailBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMessageDetailBinding.bind(view)
        viewModel = (activity as MainActivity).userViewModel

        //Navigation args
        val args: MessageDetailFragmentArgs by navArgs()
        val message = args.selectedMessage
        binding.txvTitle.text = message.title

        viewUserDetails(message.userId)
    }

    private fun viewUserDetails(id: Int) {
        viewModel.getUserById(id)
        viewModel.user.observe(viewLifecycleOwner, { response ->
            Log.d(TAG, "viewUserDetails: ${response.toString()}")
        })
    }

}