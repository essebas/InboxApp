package com.zebas2.inboxapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zebas2.inboxapp.data.model.Post
import com.zebas2.inboxapp.databinding.MessageItemBinding

class MessageAdapter : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, callback)

    fun submitList(list: List<Post>) {
        differ.submitList(list)
        notifyDataSetChanged()
    }

    fun deleteItem(post: Post) {
        differ.currentList.remove(post)
        notifyDataSetChanged()
    }

    fun getItemAtPosition(position: Int): Post {
        return differ.currentList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = MessageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = differ.currentList[position]
        holder.bind(message)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Post) -> Unit)? = null

    fun setOnItemClickListener(listener: (Post) -> Unit) {
        onItemClickListener = listener
    }

    private var onItemSwipeRightListener: ((Post) -> Unit)? = null

    fun setOnItemSwipeRightListener(listener: (Post) -> Unit) {
        onItemSwipeRightListener = listener
    }

    inner class MessageViewHolder(private val binding: MessageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.txvMessageTitle.text = post.title
            binding.txvMessageDescription.text = post.body
            binding.imgMessageRead.visibility = if (post.isRead) View.INVISIBLE else View.VISIBLE
            binding.imgMessageFav.visibility = if (post.isFavorite) View.VISIBLE else View.INVISIBLE

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    post.isRead = true
                    it(post)
                }
            }
        }

    }


}