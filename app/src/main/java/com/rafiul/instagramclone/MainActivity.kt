package com.rafiul.instagramclone

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.FirebaseAuth
import com.rafiul.instagramclone.databinding.ActivityMainBinding
import com.rafiul.instagramclone.screens.activities.HomeActivity
import com.rafiul.instagramclone.screens.activities.SignUpActivity
import com.rafiul.instagramclone.utils.navigateToNextActivityWithReplacement

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor = Color.TRANSPARENT
        Handler(Looper.getMainLooper()).postDelayed({
            checkLoginStatus()
        }, 3000)

    }
    private fun checkLoginStatus() {
        if (FirebaseAuth.getInstance().currentUser == null) {
            navigateToNextActivityWithReplacement(this@MainActivity, SignUpActivity::class.java)
        } else {
            navigateToNextActivityWithReplacement(this@MainActivity, HomeActivity::class.java)
        }
    }
}