package com.rafiul.instagramclone.screens.upload

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.rafiul.instagramclone.databinding.ActivityUploadReelsBinding
import com.rafiul.instagramclone.models.Reel
import com.rafiul.instagramclone.screens.activities.HomeActivity
import com.rafiul.instagramclone.utils.REEL
import com.rafiul.instagramclone.utils.REELS_FOLDER
import com.rafiul.instagramclone.utils.navigateToNextActivityWithReplacement
import com.rafiul.instagramclone.utils.uploadImage
import com.rafiul.instagramclone.utils.uploadVideo

class UploadReelsActivity : AppCompatActivity() {

    private val binding by lazy { ActivityUploadReelsBinding.inflate(layoutInflater) }
    private lateinit var videoUrl: String
   private lateinit var progressDialog: ProgressDialog

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadVideo(uri, REELS_FOLDER, progressDialog) { url ->
                if (url != null) {
                    videoUrl = url
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

       progressDialog =  ProgressDialog(this@UploadReelsActivity)

        setSupportActionBar(binding.materialToolbar)

        supportActionBar?.let { actionBar ->
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setDisplayShowHomeEnabled(true)
        }


        binding.apply {
            materialToolbar.setOnClickListener {
                navigateToNextActivityWithReplacement(
                    this@UploadReelsActivity,
                    HomeActivity::class.java
                )
            }

            buttonSelectReels.setOnClickListener {
                launcher.launch("video/*")
            }

            buttonCancel.setOnClickListener {
                navigateToNextActivityWithReplacement(
                    this@UploadReelsActivity,
                    HomeActivity::class.java
                )
            }

            buttonReels.setOnClickListener {
                val reel = Reel(videoUrl, userPostTextField.editText?.text.toString())
                uploadReels(reel)
            }
        }
    }

    private fun uploadReels(reel: Reel) {
        val reelCollection = Firebase.firestore.collection(REEL)
        val userCollection = Firebase.firestore.collection(Firebase.auth.currentUser!!.uid + REEL)

        reelCollection.document().set(reel)
            .addOnSuccessListener {
                userCollection.document().set(reel)
                    .addOnSuccessListener {
                        navigateToNextActivityWithReplacement(
                            this@UploadReelsActivity,
                            HomeActivity::class.java
                        )
                    }
                    .addOnFailureListener { exception ->
                        Log.e(
                            "UploadReelsActivity",
                            "Failed to set reels to user collection: ${exception.message}"
                        )

                    }
            }
            .addOnFailureListener { exception ->
                Log.e(
                    "UploadReelsActivity",
                    "Failed to set reels to user collection: ${exception.message}"
                )

            }

    }
}