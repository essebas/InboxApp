package com.zebas2.inboxapp.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zebas2.inboxapp.R
import androidx.navigation.fragment.navArgs
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import com.zebas2.inboxapp.data.model.Post
import com.zebas2.inboxapp.databinding.FragmentMessageDetailBinding
import com.zebas2.inboxapp.presentation.MainActivity
import com.zebas2.inboxapp.presentation.viewmodel.MessagesViewModel
import com.zebas2.inboxapp.presentation.viewmodel.UserViewModel

private const val TAG = "MessageDetailFragment"

class MessageDetailFragment : Fragment() {

    private lateinit var binding: FragmentMessageDetailBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var messageViewModel: MessagesViewModel

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
        userViewModel = (activity as MainActivity).userViewModel
        messageViewModel = (activity as MainActivity).messageViewModel
        (activity as MainActivity).setToolbar(binding.collapsingToolbarToolbar)

        //Navigation args
        val args: MessageDetailFragmentArgs by navArgs()
        val message = args.selectedMessage
        message.isRead = true
        binding.message = message
        //Mark read message
        messageViewModel.updatePost(message)
        viewUserDetails(message.userId)

        //listeners
        saveButtonClickListener(view, message)
    }

    private fun viewUserDetails(id: Int) {
        userViewModel.getUserById(id)
        userViewModel.user.observe(viewLifecycleOwner, { response ->
            binding.user = response
        })
    }

    private fun saveButtonClickListener(view: View, post: Post) {
        binding.btnSave.setOnClickListener {
            post.isFavorite = !post.isFavorite
            messageViewModel.updatePost(post)
            val textNotification =
                if (post.isFavorite) "Se ha guardado en favoritos" else "Se ha removido de favoritos"
            Snackbar.make(view, textNotification, Snackbar.LENGTH_LONG).show()
        }
    }

}