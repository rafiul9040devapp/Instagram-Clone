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
import com.rafiul.instagramclone.adapters.MyPostRecViewAdapter
import com.rafiul.instagramclone.databinding.FragmentMyPostBinding
import com.rafiul.instagramclone.models.Post

class MyPostFragment : Fragment() {

    private val binding: FragmentMyPostBinding by lazy { FragmentMyPostBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val postList = ArrayList<Post>()

        val postAdapter = MyPostRecViewAdapter(requireContext(), postList)

        binding.rcvPost.apply {
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            adapter = postAdapter
        }

        getPostOfUser(postList, postAdapter)

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getPostOfUser(
        postList: ArrayList<Post>,
        postAdapter: MyPostRecViewAdapter
    ) {

        Firebase.auth.currentUser?.let { user ->
            Firebase.firestore.collection(user.uid).get().addOnSuccessListener { querySnapshot ->
                val tempList = arrayListOf<Post>()
                for (document in querySnapshot.documents) {
                    val post = document.toObject<Post>()
                    post?.let { tempList.add(it) }
                }
                postList.addAll(tempList)
                postAdapter.notifyDataSetChanged()
            }
        }
    }

}