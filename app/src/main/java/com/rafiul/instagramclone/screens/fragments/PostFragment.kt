package com.rafiul.instagramclone.screens.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.rafiul.instagramclone.R
import com.rafiul.instagramclone.databinding.FragmentPostBinding
import com.rafiul.instagramclone.screens.upload.UploadPostActivity
import com.rafiul.instagramclone.screens.upload.UploadReelsActivity
import com.rafiul.instagramclone.utils.navigateToNextActivity


class PostFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentPostBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            linearLayoutAddPost.setOnClickListener {
                navigateToNextActivity(requireActivity(), UploadPostActivity::class.java)
            }

            linearLayoutAddReels.setOnClickListener {
                navigateToNextActivity(requireActivity(), UploadReelsActivity::class.java)
            }
        }
    }

    companion object {
    }
}