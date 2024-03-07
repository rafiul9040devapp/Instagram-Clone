package com.rafiul.instagramclone.screens.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.rafiul.instagramclone.adapters.SearchPersonAdapter
import com.rafiul.instagramclone.databinding.FragmentSearchBinding
import com.rafiul.instagramclone.models.User
import com.rafiul.instagramclone.utils.USER_NODE
import com.rafiul.instagramclone.utils.showLongToast

class SearchFragment : Fragment() {

    private val binding: FragmentSearchBinding by lazy {
        FragmentSearchBinding.inflate(
            layoutInflater
        )
    }

    private lateinit var searchPersonAdapter: SearchPersonAdapter
    private val personList = ArrayList<User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        getAllUsers()

        binding.searchView.setOnSearchClickListener {

        }
    }


    private fun setupRecyclerView() {
        searchPersonAdapter = SearchPersonAdapter(requireContext(), personList)
        binding.rcvPerson.apply {
            adapter = searchPersonAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun getAllUsers() {
        val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid ?: return
       Firebase.firestore.collection(USER_NODE)
            .get()
            .addOnSuccessListener { querySnapshot ->
                handleQuerySnapshot(querySnapshot, currentUserUid)
            }
            .addOnFailureListener { exception ->
                showLongToast(requireContext(),exception.toString())
            }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun handleQuerySnapshot(querySnapshot: QuerySnapshot, currentUserUid: String) {
        val tempList = mutableListOf<User>()
        querySnapshot.documents.forEach { document ->
            val userId = document.id
            if (userId != currentUserUid) {
                val user = document.toObject<User>()
                user?.let { tempList.add(it) }
            }
        }
        personList.clear()
        personList.addAll(tempList.reversed())
        searchPersonAdapter.notifyDataSetChanged()
    }
}
