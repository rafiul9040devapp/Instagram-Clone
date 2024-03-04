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
import com.rafiul.instagramclone.utils.navigateToNextActivityWithReplacement


class PostFragment : BottomSheetDialogFragment() {

    private val binding: FragmentPostBinding by lazy { FragmentPostBinding.inflate(layoutInflater) }


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

        binding.apply {

            linearLayoutAddPost.setOnClickListener {
                navigateToNextActivityWithReplacement(requireActivity(), UploadPostActivity::class.java)
            }

            linearLayoutAddReels.setOnClickListener {
                navigateToNextActivityWithReplacement(requireActivity(), UploadReelsActivity::class.java)
            }
        }
    }
}