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

class PostAdapter(private val context: Context, private val postList: List<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ViewHolderAllPostBinding) : RecyclerView.ViewHolder(binding.root) {

        private var isButtonPressed = false
        fun bindWithView(post: Post) {
            with(binding) {
                Glide.with(context)
                    .load(post.image)
                    .centerCrop()
                    .placeholder(R.drawable.user)
                    .into(imageViewProfile)

                Glide.with(context)
                    .load(post.postUrl)
                    .centerCrop()
                    .placeholder(R.drawable.loading)
                    .into(imageViewPost)

                tVUserName.text = post.name
                textViewCaption.text = post.caption

                if (post.time.isNotEmpty()) {
                    textViewPostTime.text = TimeAgo.using(post.time.toLong())
                } else {
                    textViewPostTime.text = post.time
                }

                imageViewLike.setOnClickListener {
                    isButtonPressed = !isButtonPressed
                    if (isButtonPressed) {
                        imageViewLike.setBackgroundResource(R.drawable.heart_filled)
                    } else {
                        imageViewLike.setBackgroundResource(R.drawable.heart)
                    }
                }

                imageViewShare.setOnClickListener {
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.type = "text/plain"
                    shareIntent.putExtra(Intent.EXTRA_TEXT, post.postUrl)
                    context.startActivity(shareIntent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewHolderAllPostBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = postList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindWithView(postList[position])
    }
}
