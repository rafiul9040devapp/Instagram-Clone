package com.rafiul.instagramclone.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.rafiul.instagramclone.R
import com.rafiul.instagramclone.databinding.ActivityLoginBinding
import com.rafiul.instagramclone.models.User
import com.rafiul.instagramclone.utils.getLongToast
import com.rafiul.instagramclone.utils.getShortToast
import com.rafiul.instagramclone.utils.navigateToNextActivity
import com.rafiul.instagramclone.utils.navigateToNextActivityWithReplacement

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private lateinit var userEmail: String
    private lateinit var userPassword: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
         userEmail = binding.userEmailTextField.editText?.text.toString().trim()
         userPassword = binding.passwordTextField.editText?.text.toString().trim()

        binding.apply {
            filledButtonLogin.setOnClickListener {
                if (isFieldEmpty()){
                    getLongToast(this@LoginActivity,"Please Fill All The Fields")
                }else{
                    val user = User(email = userEmail, password = userPassword)
                    checkValidUser(user)
                }
            }
        }
    }

    private fun checkValidUser(user: User) {
        Firebase.auth.signInWithEmailAndPassword(user.email ?: "", user.password ?: "")
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    navigateToNextActivityWithReplacement(
                        this@LoginActivity,
                        HomeActivity::class.java
                    )
                } else {
                    it.exception?.localizedMessage?.let { message ->
                        getShortToast(this@LoginActivity, message)
                    }
                }
            }
    }

    private fun isFieldEmpty(): Boolean {
        return userEmail.isEmpty() || userPassword.isEmpty()
    }
}