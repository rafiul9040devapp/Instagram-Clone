package com.rafiul.instagramclone.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.rafiul.instagramclone.screens.fragments.ProfileFragment

fun navigateToNextActivity(currentActivity: Activity, destinationActivity: Class<*>) {
    val intent = Intent(currentActivity, destinationActivity)
    currentActivity.startActivity(intent)
}

fun navigateToNextActivityWithReplacement(currentActivity: Activity, destinationActivity: Class<*>) {
    val intent = Intent(currentActivity, destinationActivity)
    currentActivity.startActivity(intent)
    currentActivity.finish()
}

fun navigateToNextActivityWithData(currentActivity: Activity, destinationActivity: Class<*>, data: Bundle?) {
    val intent = Intent(currentActivity, destinationActivity)
    data?.let { bundleData ->
        intent.putExtras(bundleData)
    }
    currentActivity.startActivity(intent)
}

fun navigateToNextActivityWithReplacementAndData(currentActivity: Activity, destinationActivity: Class<*>, data: Bundle?) {
    val intent = Intent(currentActivity, destinationActivity)
    data?.let { bundleData ->
        intent.putExtras(bundleData)
    }
    currentActivity.startActivity(intent)
    currentActivity.finish()
}



//implementation

//val dataBundle = Bundle().apply {
//    putString("key", "value")
//    putInt("another_key", 123)
//    // Add more data as needed
//}
//
//navigateToNextActivityWithData(this@LoginActivity, HomeActivity::class.java, dataBundle)
