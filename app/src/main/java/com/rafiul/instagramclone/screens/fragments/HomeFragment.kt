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
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.rafiul.instagramclone.R
import com.rafiul.instagramclone.adapters.FollowedPersonAdapter
import com.rafiul.instagramclone.adapters.PostAdapter
import com.rafiul.instagramclone.databinding.FragmentHomeBinding
import com.rafiul.instagramclone.models.Post
import com.rafiul.instagramclone.models.User
import com.rafiul.instagramclone.utils.FOLLOW
import com.rafiul.instagramclone.utils.POST
import com.rafiul.instagramclone.utils.USER_NODE


class HomeFragment : Fragment() {

    private val binding: FragmentHomeBinding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private var postList = ArrayList<Post>()
    private var personList = ArrayList<User>()
    private lateinit var postAdapter: PostAdapter
    private lateinit var followedPersonAdapter: FollowedPersonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        postAdapter = PostAdapter(requireContext(), postList)
        followedPersonAdapter = FollowedPersonAdapter(requireContext(), personList)

        with(binding) {
            rcvAllPosts.apply {
                adapter = postAdapter
            }

            rcvPeople.apply {
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = followedPersonAdapter
            }


            Firebase.firestore.collection(USER_NODE)
                .document(FirebaseAuth.getInstance().currentUser!!.uid).get().addOnSuccessListener {
                    val user: User = it.toObject<User>()!!
                    Glide.with(requireContext())
                        .load(user.image)
                        .centerCrop()
                        .placeholder(R.drawable.user)
                        .into(imageViewProfile)
                }
        }

        getAllPost()
        getAllPerson()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getAllPerson() {
        Firebase.firestore.collection(FirebaseAuth.getInstance().currentUser?.uid + FOLLOW).get()
            .addOnSuccessListener { querySnapshot ->
                val tempList = arrayListOf<User>()
                personList.clear()
                for (document in querySnapshot.documents) {
                    val person = document.toObject<User>()
                    person?.let { tempList.add(it) }
                }
                personList.addAll(tempList)
                personList.reverse()
                followedPersonAdapter.notifyDataSetChanged()
            }
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

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

}