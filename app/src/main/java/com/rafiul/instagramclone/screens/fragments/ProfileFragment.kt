package com.rafiul.instagramclone.screens.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.rafiul.instagramclone.databinding.FragmentProfileBinding
import com.rafiul.instagramclone.models.User
import com.rafiul.instagramclone.screens.activities.SignUpActivity
import com.rafiul.instagramclone.utils.USER_NODE
import com.rafiul.instagramclone.utils.navigateToNextActivityWithData
import com.rafiul.instagramclone.utils.navigateToNextActivityWithReplacementAndData
import com.squareup.picasso.Picasso


class ProfileFragment : Fragment() {

    companion object {
    }

    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.apply {
            buttonEditAccount.setOnClickListener {
                val dataBundle = Bundle().apply {
                    putInt("Mode", 1)
                }
                activity?.let { it1 -> navigateToNextActivityWithReplacementAndData(it1,SignUpActivity::class.java,dataBundle) }
            }
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        Firebase.firestore.collection(USER_NODE).document(FirebaseAuth.getInstance().currentUser!!.uid).get().addOnSuccessListener {
            val user: User = it.toObject<User>()!!
            binding.apply {
                tVUserName.text = user.name
                tVUserBio.text = user.email
                if (!user.image.isNullOrBlank()){
                   Picasso.get().load(user.image).into(imageViewProfile)
                }
            }
        }
    }

}