package com.rafiul.instagramclone.screens.upload

import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.rafiul.instagramclone.databinding.ActivityUploadPostBinding
import com.rafiul.instagramclone.models.Post
import com.rafiul.instagramclone.utils.POST
import com.rafiul.instagramclone.utils.POST_FOLDER
import com.rafiul.instagramclone.utils.uploadImage

class UploadPostActivity : AppCompatActivity() {

    private val binding: ActivityUploadPostBinding by lazy {
        ActivityUploadPostBinding.inflate(
            layoutInflater
        )
    }

    private var imageUrl: String? = null

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, POST_FOLDER) { url ->
                if (url != null) {
                    binding.imageView1.setImageURI(uri)
                    imageUrl = url
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.materialToolbar)

        supportActionBar?.let { actionBar ->
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowHomeEnabled(true)
        }

        binding.apply {
            materialToolbar.setOnClickListener {
                finish()
            }

            imageView1.setOnClickListener {
                launcher.launch("image/*")
            }

            buttonPost.setOnClickListener {

                //post are added properly
                //also kept the post user wise

                val post: Post = Post(imageUrl ?: "",userPostTextField.editText?.text.toString())
                uploadPost(post)

            }
        }
    }

    private fun uploadPost(post: Post) {
        Firebase.firestore.collection(POST).document().set(post).addOnSuccessListener {
            Firebase.firestore.collection(Firebase.auth.currentUser!!.uid).document().set(post)
                .addOnSuccessListener {
                    finish()
                }
        }
    }
}