package com.rafiul.instagramclone.screens.upload

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rafiul.instagramclone.R
import com.rafiul.instagramclone.databinding.ActivityUploadReelsBinding

class UploadReelsActivity : AppCompatActivity() {

    private val binding by lazy { ActivityUploadReelsBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}