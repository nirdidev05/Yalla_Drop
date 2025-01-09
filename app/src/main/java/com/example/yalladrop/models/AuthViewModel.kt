package com.example.yalladrop.models

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val context: Context
) : ViewModel() {

    private val sharedPrefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    fun isUserLoggedIn(): Boolean {
        return sharedPrefs.getBoolean("is_logged_in", false)
    }

    fun getUserId(): String? {
        return sharedPrefs.getString("user_id", null)
    }

    fun login(userId: String) {
        sharedPrefs.edit()
            .putBoolean("is_logged_in", true)
            .putString("user_id", userId)  // Store the userId here
            .apply()
    }

    fun logout() {
        sharedPrefs.edit()
            .putBoolean("is_logged_in", false)
            .remove("user_id")  // Remove the userId when logging out
            .apply()
    }
}
