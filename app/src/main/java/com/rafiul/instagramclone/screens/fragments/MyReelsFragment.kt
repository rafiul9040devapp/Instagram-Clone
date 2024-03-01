package com.rafiul.instagramclone.screens.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rafiul.instagramclone.R
import com.rafiul.instagramclone.databinding.FragmentMyReelsBinding

class MyReelsFragment : Fragment() {

    private lateinit var binding: FragmentMyReelsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyReelsBinding.inflate(inflater, container, false)
        return  binding.root
    }

    companion object {
    }
}