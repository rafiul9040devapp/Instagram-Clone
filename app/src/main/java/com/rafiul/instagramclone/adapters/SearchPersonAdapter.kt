package com.rafiul.instagramclone.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.rafiul.instagramclone.R
import com.rafiul.instagramclone.databinding.ViewHolderPersonBinding
import com.rafiul.instagramclone.models.User
import com.rafiul.instagramclone.utils.FOLLOW

class SearchPersonAdapter(val context: Context, private var personList: ArrayList<User>) :
    RecyclerView.Adapter<SearchPersonAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ViewHolderPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(user: User) {
            var isFollow = false
            with(binding) {
                Glide.with(context)
                    .load(user.image)
                    .centerCrop()
                    .placeholder(R.drawable.user)
                    .into(imageViewProfile)

                textViewUserName.text = user.name ?: ""

                Firebase.firestore.collection(FirebaseAuth.getInstance().currentUser?.uid + FOLLOW)
                    .whereEqualTo("email", user.email).get().addOnSuccessListener {
                        if (it.documents.size == 0) {
                            isFollow = false
                        } else {
                            button.text = "Unfollow"
                        }
                    }


                button.setOnClickListener {

                    if (isFollow) {
                        Firebase.firestore.collection(FirebaseAuth.getInstance().currentUser?.uid + FOLLOW)
                            .whereEqualTo("email", user.email).get().addOnSuccessListener {
                                Firebase.firestore.collection(FirebaseAuth.getInstance().currentUser?.uid + FOLLOW)
                                    .document(it.documents[0].id).delete()
                                button.text = "Follow"
                                isFollow = false
                            }

                    } else {
                        Firebase.firestore.collection(FirebaseAuth.getInstance().currentUser?.uid + FOLLOW)
                            .document().set(user)
                        button.text = "Unfollow"
                        isFollow = true
                    }

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ViewHolderPersonBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = personList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = personList[position]
        holder.bind(user = person)
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setFilteredList(personList: ArrayList<User>) {
        this.personList = personList
        notifyDataSetChanged()
    }
}