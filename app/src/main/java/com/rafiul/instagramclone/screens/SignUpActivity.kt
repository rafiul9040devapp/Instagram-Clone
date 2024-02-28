package com.rafiul.instagramclone.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.rafiul.instagramclone.R
import com.rafiul.instagramclone.databinding.ActivitySignUpBinding
import com.rafiul.instagramclone.models.User
import com.rafiul.instagramclone.utils.USER_NODE
import com.rafiul.instagramclone.utils.USER_PROFILE_FOLDER
import com.rafiul.instagramclone.utils.uploadImage

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var user: User

    private val launcher = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            uploadImage(uri, USER_PROFILE_FOLDER) { imageUrl ->
                if (imageUrl == null) {
                    Toast.makeText(this, "Invalid Image Url", Toast.LENGTH_LONG).show()
                } else {
                    user.image = imageUrl
                    binding.profileImage.setImageURI(uri)
                }
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = User()

        binding.filledButtonRegister.setOnClickListener {
            if (isAnyFieldEmpty()) {
                Toast.makeText(this, "Please Fill The Input Fields", Toast.LENGTH_LONG).show()
            } else {
                getLoginStatus()
            }
        }

        binding.addImage.setOnClickListener {
            launcher.launch("image/*")
        }
    }

    private fun getLoginStatus() {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(
            binding.userEmailTextField.editText?.text.toString().trim(),
            binding.passwordTextField.editText?.text.toString().trim()
        ).addOnCompleteListener { result ->
            if (result.isSuccessful) {
                user.name = binding.userNameTextField.editText?.text.toString().trim()
                user.email = binding.userEmailTextField.editText?.text.toString().trim()
                user.password = binding.passwordTextField.editText?.text.toString().trim()
//                user = User(userName, userEmail, password)
                Firebase.firestore.collection(USER_NODE)
                    .document(Firebase.auth.currentUser!!.uid)
                    .set(user).addOnCompleteListener {
                        Toast.makeText(this, "Login SuccessFully", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this,HomeActivity::class.java))
                    }
            } else {
                Toast.makeText(this, result.exception?.localizedMessage, Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun isAnyFieldEmpty(): Boolean {
        val userName = binding.userNameTextField.editText?.text.toString()
        val userEmail = binding.userEmailTextField.editText?.text.toString()
        val password = binding.passwordTextField.editText?.text.toString()
        return userName.isEmpty() || userEmail.isEmpty() || password.isEmpty()
    }

}