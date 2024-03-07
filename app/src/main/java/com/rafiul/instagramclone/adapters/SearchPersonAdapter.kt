package com.rafiul.instagramclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rafiul.instagramclone.R
import com.rafiul.instagramclone.databinding.ViewHolderPersonBinding
import com.rafiul.instagramclone.models.User

class SearchPersonAdapter(val context: Context, private val personList : ArrayList<User>) : RecyclerView.Adapter<SearchPersonAdapter.ViewHolder>()  {


    inner class ViewHolder(val binding: ViewHolderPersonBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user: User){
            with(binding){

                Glide.with(context)
                    .load(user.image)
                    .centerCrop()
                    .placeholder(R.drawable.user)
                    .into(imageViewProfile)

                textViewUserName.text = user.name ?: ""
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ViewHolderPersonBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int  = personList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = personList[position]
        holder.bind(user = person)
    }

}