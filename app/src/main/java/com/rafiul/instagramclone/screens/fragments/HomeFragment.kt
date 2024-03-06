package com.rafiul.instagramclone.screens.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.rafiul.instagramclone.R
import com.rafiul.instagramclone.adapters.PostAdapter
import com.rafiul.instagramclone.databinding.FragmentHomeBinding
import com.rafiul.instagramclone.models.Post
import com.rafiul.instagramclone.utils.POST


class HomeFragment : Fragment() {

    private val binding: FragmentHomeBinding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private var postList = ArrayList<Post>()
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        postAdapter = PostAdapter(requireContext(),postList)

        binding.rceAllPosts.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = postAdapter
        }
        getAllPost()
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getAllPost() {
        Firebase.firestore.collection(POST).get().addOnSuccessListener { querySnapshot ->
            val tempList = arrayListOf<Post>()
            postList.clear()
            for (document in querySnapshot.documents) {
                val post = document.toObject<Post>()
                post?.let { tempList.add(it) }
            }
            postList.addAll(tempList)
            postList.reverse()
            postAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}