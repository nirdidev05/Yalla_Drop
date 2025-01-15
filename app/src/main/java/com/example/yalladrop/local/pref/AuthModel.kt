package com.example.yalladrop.local.pref

import android.content.Context
import android.content.SharedPreferences

public class AuthManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        PREF_NAME,
        Context.MODE_PRIVATE
    )

    fun saveUserSession(email: String , name : String , phone : String , id : String) {
        with(sharedPreferences.edit()) {
            putBoolean(KEY_IS_LOGGED_IN, true)
            putString(KEY_USER_EMAIL, email)
            putString(KEY_USER_NAME , name)
            putString(KEY_USER_PHONE , phone)
            putString(KEY_USER_ID , id)
            apply()
        }
    }

    fun updateUserNamePhone(name : String , phone: String){
        with(sharedPreferences.edit()) {
            putString(KEY_USER_NAME , name)
            putString(KEY_USER_NAME , phone)
            apply()
        }
    }


    fun getUserEmail(): String? {
        return sharedPreferences.getString(KEY_USER_EMAIL, null)
    }
    fun getUserName(): String? {
        return sharedPreferences.getString(KEY_USER_NAME, null)
    }

    fun getUserPhone(): String? {
        return sharedPreferences.getString(KEY_USER_PHONE, null)
    }
    fun getUserID(): String? {
        return sharedPreferences.getString(KEY_USER_ID, null)
    }
    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun clearSession() {
        with(sharedPreferences.edit()) {
            clear()
            apply()
        }
    }

    companion object {
        private const val PREF_NAME = "YallaDropPrefs"
        private const val KEY_IS_LOGGED_IN = "isLoggedIn"
        private const val KEY_USER_EMAIL = "userEmail"
        private const val KEY_USER_NAME = "userName"
        private const val KEY_USER_PHONE = "userPhone"
        private const val KEY_USER_ID = "userID"

    }
}