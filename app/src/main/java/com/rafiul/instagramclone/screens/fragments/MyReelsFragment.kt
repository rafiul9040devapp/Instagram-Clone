package com.rafiul.instagramclone.screens.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.rafiul.instagramclone.adapters.MyReelsRcvViewAdapter
import com.rafiul.instagramclone.databinding.FragmentMyReelsBinding
import com.rafiul.instagramclone.models.Reel
import com.rafiul.instagramclone.utils.REEL

class MyReelsFragment : Fragment() {

    private val binding: FragmentMyReelsBinding by lazy {
        FragmentMyReelsBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val reelsList = ArrayList<Reel>()
        val reelsAdapter = MyReelsRcvViewAdapter(requireContext(), reelsList)
        binding.rcvReels.apply {
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            adapter = reelsAdapter
        }
        getReelsOfUser(reelsList, reelsAdapter)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getReelsOfUser(reelsList: ArrayList<Reel>, reelsAdapter: MyReelsRcvViewAdapter) {

        Firebase.auth.currentUser?.let { user ->
            Firebase.firestore.collection(user.uid + REEL).get()
                .addOnSuccessListener { querySnapshot ->
                    val tempList = arrayListOf<Reel>()
                    for (document in querySnapshot.documents) {
                        val reel = document.toObject<Reel>()
                        reel?.let { tempList.add(it) }
                    }
                    reelsList.addAll(tempList)
                    reelsAdapter.notifyDataSetChanged()
                }
        }
    }
}