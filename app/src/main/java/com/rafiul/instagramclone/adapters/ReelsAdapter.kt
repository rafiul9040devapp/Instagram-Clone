package com.rafiul.instagramclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafiul.instagramclone.R
import com.rafiul.instagramclone.databinding.ViewHolderReelsBinding
import com.rafiul.instagramclone.models.Reel
import com.squareup.picasso.Picasso

class ReelsAdapter(val context: Context, private val reelsList: List<Reel>) :
    RecyclerView.Adapter<ReelsAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ViewHolderReelsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ViewHolderReelsBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return reelsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reel = reelsList[position]
        holder.binding.apply {
            Picasso.get()
                .load(reel.profileLink)
                .placeholder(R.drawable.profile)
                .into(imageViewProfile)
            textViewCaption.text = reel.caption
            videoViewReels.apply {
                setVideoPath(reel.reelUrl)
                setOnPreparedListener {
                    progressBar.visibility = View.GONE
                    videoViewReels.start()
                    videoViewReels.seekTo(1)
                }
            }
        }
    }


}