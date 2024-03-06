package com.rafiul.instagramclone.screens.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.rafiul.instagramclone.adapters.ViewPagerAdapter
import com.rafiul.instagramclone.databinding.FragmentProfileBinding
import com.rafiul.instagramclone.models.User
import com.rafiul.instagramclone.screens.activities.SignUpActivity
import com.rafiul.instagramclone.utils.USER_NODE
import com.rafiul.instagramclone.utils.navigateToNextActivityWithReplacementAndData
import com.squareup.picasso.Picasso


class ProfileFragment : Fragment() {

    companion object {
        val tabsOfProfile = arrayOf(
            "My Post" ,
            "My Reels"
        )
    }

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ViewPagerAdapter(requireActivity().supportFragmentManager,lifecycle)
        binding.viewpager.adapter = adapter
        TabLayoutMediator(binding.tab, binding.viewpager) { tab, position ->
            tab.text = tabsOfProfile[position]
        }.attach()

        binding.apply {
            buttonEditAccount.setOnClickListener {
                val dataBundle = Bundle().apply {
                    putInt("Mode", 1)
                }
                activity?.let { it1 -> navigateToNextActivityWithReplacementAndData(it1,SignUpActivity::class.java,dataBundle) }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        fetchUserDetails()
    }

    private fun fetchUserDetails() {
        Firebase.firestore.collection(USER_NODE)
            .document(FirebaseAuth.getInstance().currentUser!!.uid).get().addOnSuccessListener {
                val user: User = it.toObject<User>()!!
                binding.apply {
                    tVUserName.text = user.name
                    tVUserBio.text = user.email
                    if (!user.image.isNullOrBlank()) {
                        Picasso.get().load(user.image).into(imageViewProfile)
                    }
                }
            }
    }

}