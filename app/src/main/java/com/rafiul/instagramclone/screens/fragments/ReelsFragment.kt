package com.rafiul.instagramclone.screens.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.rafiul.instagramclone.adapters.ReelsAdapter
import com.rafiul.instagramclone.databinding.FragmentReelsBinding
import com.rafiul.instagramclone.models.Reel
import com.rafiul.instagramclone.models.User
import com.rafiul.instagramclone.utils.REEL
import com.rafiul.instagramclone.utils.USER_NODE

class ReelsFragment : Fragment() {

    private val binding: FragmentReelsBinding by lazy { FragmentReelsBinding.inflate(layoutInflater) }
    private val reelsList = ArrayList<Reel>()
    private lateinit var reelsAdapter : ReelsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        reelsAdapter = ReelsAdapter(requireContext(),reelsList)
        binding.viewPager.adapter = reelsAdapter

        getAllReels(reelsList,reelsAdapter)


        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getAllReels(reelsList:ArrayList<Reel>, reelsAdapter: ReelsAdapter) {
        Firebase.firestore.collection(REEL).get()
            .addOnSuccessListener { querySnapshot ->
                val tempList = arrayListOf<Reel>()
                reelsList.clear()
                for (document in querySnapshot.documents) {
                    val reel = document.toObject<Reel>()
                    reel?.let { tempList.add(it) }
                }

//                reelsList.apply {
//                    addAll(tempList)
//                    reversed()
//                }
                reelsList.addAll(tempList)
                reelsList.reversed()
                reelsAdapter.notifyDataSetChanged()
            }


    }
}