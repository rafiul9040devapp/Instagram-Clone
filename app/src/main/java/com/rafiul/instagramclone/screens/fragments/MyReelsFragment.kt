package com.rafiul.instagramclone.screens.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rafiul.instagramclone.R
import com.rafiul.instagramclone.adapters.MyReelsRcvViewAdapter
import com.rafiul.instagramclone.databinding.FragmentMyReelsBinding

class MyReelsFragment : Fragment() {

    private val binding: FragmentMyReelsBinding by lazy { FragmentMyReelsBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val reelsList = ArrayList<String>()

        val reelsAdapter = MyReelsRcvViewAdapter(requireContext(),reelsList)

        binding.rcvReels.apply {
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            adapter = reelsAdapter
        }

        getReelsOfUser(reelsList,reelsAdapter)

        return  binding.root
    }

    private fun getReelsOfUser(reelsList: ArrayList<String>, reelsAdapter: MyReelsRcvViewAdapter) {}
}