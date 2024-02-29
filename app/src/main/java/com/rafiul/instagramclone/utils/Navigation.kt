package com.rafiul.instagramclone.utils

import android.app.Activity
import android.content.Intent

fun navigateToNextActivity(currentActivity: Activity, destinationActivity: Class<*>) {
    val intent = Intent(currentActivity, destinationActivity)
    currentActivity.startActivity(intent)
}

fun navigateToNextActivityWithReplacement(currentActivity: Activity, destinationActivity: Class<*>) {
    val intent = Intent(currentActivity, destinationActivity)
    currentActivity.startActivity(intent)
    currentActivity.finish()
}
