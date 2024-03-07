package com.rafiul.instagramclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rafiul.instagramclone.R
import com.rafiul.instagramclone.databinding.ViewHolderAllPostBinding
import com.rafiul.instagramclone.models.Post
import com.squareup.picasso.Picasso

class PostAdapter(val context: Context, private val postList: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ViewHolderAllPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ViewHolderAllPostBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int {
       return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val post = postList[position]
        holder.binding.apply {
            Glide
                .with(context)
                .load(post.image)
                .centerCrop()
                .placeholder(R.drawable.user)
                .into(imageViewProfile)

            Glide
                .with(context)
                .load(post.postUrl)
                .centerCrop()
                .placeholder(R.drawable.loading)
                .into(imageViewPost)

            tVUserName.text = post.name
            textViewCaption.text = post.caption
            textViewPostTime.text = post.time
        }
    }


}