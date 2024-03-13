package com.rafiul.instagramclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rafiul.instagramclone.R
import com.rafiul.instagramclone.databinding.ViewHolderFollowedPersonBinding
import com.rafiul.instagramclone.models.User

class FollowedPersonAdapter(private val context: Context, private val personList: ArrayList<User>) :
    RecyclerView.Adapter<FollowedPersonAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ViewHolderFollowedPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindWithView(user: User) {
            with(binding) {
                textViewUserName.text = user.name
                Glide.with(context)
                    .load(user.image)
                    .centerCrop()
                    .placeholder(R.drawable.user)
                    .into(imageViewProfile)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ViewHolderFollowedPersonBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return personList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindWithView(personList[position])
    }

}