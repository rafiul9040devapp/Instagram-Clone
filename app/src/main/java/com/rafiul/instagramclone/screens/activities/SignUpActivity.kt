package com.rafiul.instagramclone.screens.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.text.HtmlCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.rafiul.instagramclone.databinding.ActivitySignUpBinding
import com.rafiul.instagramclone.models.User
import com.rafiul.instagramclone.utils.USER_NODE
import com.rafiul.instagramclone.utils.USER_PROFILE_FOLDER
import com.rafiul.instagramclone.utils.showLongToast
import com.rafiul.instagramclone.utils.showShortToast
import com.rafiul.instagramclone.utils.navigateToNextActivity
import com.rafiul.instagramclone.utils.navigateToNextActivityWithReplacement
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

        val text =
            "<font color=#FF000000>Already Have An Account</font> <font color=#077DB3>Login?</font>"
        binding.textViewLogin.text = HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)

        user = User()

        binding.apply {

            filledButtonRegister.setOnClickListener {
                if (isAnyFieldEmpty()) {
                    showShortToast(this@SignUpActivity, "Please Fill The Input Fields")
                } else {
                    getLoginStatus()
                }
            }

            addImage.setOnClickListener {
                launcher.launch("image/*")
            }

            textViewLogin.setOnClickListener {
                navigateToNextActivity(this@SignUpActivity, LoginActivity::class.java)
            }

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
                Firebase.firestore.collection(USER_NODE)
                    .document(Firebase.auth.currentUser!!.uid)
                    .set(user).addOnCompleteListener {
                        showShortToast(this@SignUpActivity, "Login SuccessFully")
                        navigateToNextActivityWithReplacement(
                            this@SignUpActivity,
                            HomeActivity::class.java
                        )
                    }
            } else {
                result.exception?.localizedMessage?.let { message ->
                    showLongToast(this@SignUpActivity, message)
                }
            }
        }
    }

    private fun isAnyFieldEmpty(): Boolean {
        val userName = binding.userNameTextField.editText?.text.toString().trim()
        val userEmail = binding.userEmailTextField.editText?.text.toString().trim()
        val password = binding.passwordTextField.editText?.text.toString().trim()
        return userName.isEmpty() || userEmail.isEmpty() || password.isEmpty()
    }

}