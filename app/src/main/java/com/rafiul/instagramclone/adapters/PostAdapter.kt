package com.rafiul.instagramclone.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.rafiul.instagramclone.R
import com.rafiul.instagramclone.databinding.ViewHolderAllPostBinding
import com.rafiul.instagramclone.models.Post
import com.squareup.picasso.Picasso

class PostAdapter(val context: Context, private val postList: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ViewHolderAllPostBinding) : RecyclerView.ViewHolder(binding.root){
        var isButtonPressed = false
    }

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

            if (post.time != ""){
                textViewPostTime.text = TimeAgo.using(post.time.toLong())
            }else{
                textViewPostTime.text = post.time
            }

            imageViewLike.setOnClickListener {
                holder.isButtonPressed = !holder.isButtonPressed
                if (holder.isButtonPressed) {
                   imageViewLike.setBackgroundResource(R.drawable.heart_filled)
                } else {
                    imageViewLike.setBackgroundResource(R.drawable.heart)
                }
            }

            imageViewShare.setOnClickListener {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT,post.postUrl)
                context.startActivity(shareIntent)
            }
        }
    }
}