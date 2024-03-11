package com.rafiul.instagramclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rafiul.instagramclone.R
import com.rafiul.instagramclone.databinding.ViewHolderPersonBinding
import com.rafiul.instagramclone.models.User

class SearchPersonAdapterAlternative(val context: Context) :
    ListAdapter<User, SearchPersonAdapterAlternative.ViewHolder>(COMPARATOR) {

    inner class ViewHolder(val binding: ViewHolderPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindWithView(user: User) {
            with(binding) {
                Glide.with(context)
                    .load(user.image)
                    .centerCrop()
                    .placeholder(R.drawable.user)
                    .into(imageViewProfile)
                textViewUserName.text = user.name ?: ""
            }
        }
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.email == newItem.email
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ViewHolderPersonBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindWithView(getItem(position))
    }
}
