package com.rafiul.instagramclone.utils

import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID


fun uploadImage(uri: Uri, folderName: String, callback: (String?) -> Unit) {

    var imageUrl: String? = null

    FirebaseStorage.getInstance().getReference(folderName).child(UUID.randomUUID().toString())
        .putFile(uri)
        .addOnSuccessListener { uploadTask ->
            uploadTask.storage.downloadUrl.addOnSuccessListener { uri ->
                imageUrl = uri.toString()
                callback(imageUrl)
            }.addOnFailureListener {
                callback(null)
            }
        }.addOnFailureListener {
            callback(null)
        }
}



fun showLongToast(context: Context, message:String) =  Toast.makeText(context,message, Toast.LENGTH_LONG).show()
fun showShortToast(context: Context, message:String) = Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
