package com.rafiul.instagramclone.screens.upload

import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.type.DateTime
import com.rafiul.instagramclone.databinding.ActivityUploadPostBinding
import com.rafiul.instagramclone.models.Post
import com.rafiul.instagramclone.models.User
import com.rafiul.instagramclone.screens.activities.HomeActivity
import com.rafiul.instagramclone.utils.POST
import com.rafiul.instagramclone.utils.POST_FOLDER
import com.rafiul.instagramclone.utils.USER_NODE
import com.rafiul.instagramclone.utils.navigateToNextActivityWithReplacement
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

    private lateinit var post: Post

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
                navigateToNextActivityWithReplacement(this@UploadPostActivity,HomeActivity::class.java)
            }

            imageView1.setOnClickListener {
                launcher.launch("image/*")
            }

            buttonPost.setOnClickListener {
                //post are added properly
                //also kept the post user wise
                Firebase.firestore.collection(USER_NODE).document(Firebase.auth.currentUser!!.uid).get().addOnSuccessListener {
                    val user = it.toObject<User>()
                    if (user != null) {
                        post= Post(imageUrl ?: "",userPostTextField.editText?.text.toString(),user.name ?: "" ,System.currentTimeMillis().toString(),user.image ?: "")
                    }
                }
                uploadPost(post)
            }

            buttonCancel.setOnClickListener {
                navigateToNextActivityWithReplacement(this@UploadPostActivity,HomeActivity::class.java)
            }
        }
    }

    private fun uploadPost(post: Post) {
        val postCollection = Firebase.firestore.collection(POST)
        val userCollection = Firebase.firestore.collection(Firebase.auth.currentUser!!.uid)

        postCollection.document().set(post)
            .addOnSuccessListener {
                userCollection.document().set(post)
                    .addOnSuccessListener {
                        navigateToNextActivityWithReplacement(this@UploadPostActivity, HomeActivity::class.java)
                    }
                    .addOnFailureListener { exception ->
                        Log.e("UploadPostActivity", "Failed to set post to user collection: ${exception.message}")
                    }
            }
            .addOnFailureListener { exception ->
                Log.e("UploadPostActivity", "Failed to set post to post collection: ${exception.message}")
            }
    }
}