package com.zebas2.inboxapp.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.zebas2.inboxapp.R
import com.zebas2.inboxapp.databinding.FragmentMessagesBinding
import com.zebas2.inboxapp.presentation.MainActivity
import com.zebas2.inboxapp.presentation.adapter.MessageAdapter
import com.zebas2.inboxapp.presentation.utils.SwipeGesture
import com.zebas2.inboxapp.presentation.viewmodel.MessagesViewModel

private const val TAG = "MessagesFragment"

class MessagesFragment : Fragment() {

    private lateinit var viewModel: MessagesViewModel
    private lateinit var binding: FragmentMessagesBinding
    private lateinit var messageAdapter: MessageAdapter

    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
        //binding.appBar.inflateMenu(R.menu.main_menu)
        (activity as MainActivity).setSupportActionBar(binding.appBar)


        initRecyclerView()
        viewMessagesList()
        setItemClickListener()
        setItemSwipeListener(view)
    }

    fun viewMessagesList() {
        setProgressBarVisibility(View.VISIBLE)
        viewModel.getMessages()
        viewModel.messages.observe(viewLifecycleOwner, { response ->
            Log.d(TAG, "viewMessagesList: ${response.toString()}")
            messageAdapter.submitList(response)
            setProgressBarVisibility(View.INVISIBLE)
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

    private fun setItemSwipeListener(view: View) {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val post = messageAdapter.getItemAtPosition(position)
                viewModel.deletePost(post)
                Snackbar.make(view, "Mensaje eliminado correctamente", Snackbar.LENGTH_LONG)
                    .apply {
                        setAction("DESHACER") {
                            viewModel.savePost(post)
                        }
                    }.show()
                viewModel.getMessages()
            }
        }
        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.recyclerMessagesList)
        }
    }

    private fun setProgressBarVisibility(visibility: Int) {
        isLoading = visibility == View.VISIBLE
        binding.progressBar.visibility = visibility
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        //super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_action_filter_fav -> {
                setProgressBarVisibility(View.VISIBLE)
                viewModel.getFavoritesMessages()
            }
            R.id.menu_action_delete_all -> {
                Toast.makeText(this.context, "Wait for implement :c", Toast.LENGTH_SHORT).show()
            }
            R.id.menu_action_filter_all -> {
                setProgressBarVisibility(View.VISIBLE)
                viewModel.getMessages()
            }
            R.id.menu_action_reload_all -> {
                setProgressBarVisibility(View.VISIBLE)
                viewModel.reloadFromServer()
            }
        }
        return false
    }

}