package com.rafiul.instagramclone

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.rafiul.instagramclone.databinding.ActivityMainBinding
import com.rafiul.instagramclone.screens.SignUpActivity

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        window.statusBarColor = Color.TRANSPARENT
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@MainActivity, SignUpActivity::class.java))
            finish()
        }, 3000)

    }
}