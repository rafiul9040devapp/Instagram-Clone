package com.rafiul.instagramclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafiul.instagramclone.databinding.ViewHolderMyPostBinding
import com.rafiul.instagramclone.models.Post
import com.squareup.picasso.Picasso

class MyPostRecViewAdapter(var context: Context, var postList: ArrayList<Post>) :
    RecyclerView.Adapter<MyPostRecViewAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ViewHolderMyPostBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ViewHolderMyPostBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int {
       return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.imageViewPost.let { imageView ->
            Picasso.get().load(postList[position].postUrl).into(imageView)
        }
    }
}