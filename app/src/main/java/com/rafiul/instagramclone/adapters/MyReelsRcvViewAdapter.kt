package com.rafiul.instagramclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafiul.instagramclone.databinding.ViewHolderMyReelsBinding

class MyReelsRcvViewAdapter(val context: Context, private val reelList: List<String>) :
    RecyclerView.Adapter<MyReelsRcvViewAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ViewHolderMyReelsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ViewHolderMyReelsBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return reelList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reel = reelList[position]
        holder.binding.apply {
        }

    }
}