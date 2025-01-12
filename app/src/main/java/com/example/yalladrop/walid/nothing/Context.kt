package com.example.yalladrop.walid.nothing

import android.content.Context
import androidx.core.content.edit

object Prefe {
    lateinit var context: Context

    private const val PREF_NAME = "pref"
    private const val KEY_PASSWORD = "Password"
    private const val KEY_EMAIL = "email"
    private const val KEY_IS_LOGGED_IN = "is_logged_in"

    fun savePassword(password: String) {
        val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        pref.edit {
            putString(KEY_PASSWORD, password)
        }
    }

    fun getPassword(): String? {
        val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return pref.getString(KEY_PASSWORD, "")
    }

    fun saveEmail(email: String) {
        val pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
        pref.edit().putString("email", email).apply()
    }

    fun getEmail(): String? {
        val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return pref.getString(KEY_EMAIL, "")
    }

    fun saveUserSession(isLoggedIn: Boolean) {
        val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        pref.edit {
            putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
        }
    }

    fun isUserLoggedIn(): Boolean {
        val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return pref.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun clearUserSession(context: Context) {
        val pref = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
        pref.edit().clear().apply() // Nettoie toutes les préférences sauvegardées
    }

}

