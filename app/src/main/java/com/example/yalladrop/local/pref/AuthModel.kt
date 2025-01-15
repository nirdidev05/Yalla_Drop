package com.example.yalladrop.local.pref

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.ui.input.key.Key

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
            putBoolean(KEY_USER_CART_NOT_EMPTY , false)
            putString(KEY_USER_CART_RESTAURANT_ID, null)
            putString(KEY_USER_CART_RESTAURANT_NAME , null)
            putString(KEY_USER_CART_ADR , null)
            apply()
        }
    }

    fun getAdr():String?{
        return sharedPreferences.getString(KEY_USER_CART_ADR , null)
    }
    fun updateADR(adr : String?){
        with(sharedPreferences.edit()) {
            putString(KEY_USER_CART_ADR , adr)
            apply()
        }
    }

    fun getCartNotEmpty():Boolean{
        return sharedPreferences.getBoolean(KEY_USER_CART_NOT_EMPTY , false)
    }
    fun updateCartNotEmpty(update : Boolean){
        with(sharedPreferences.edit()) {
            putBoolean(KEY_USER_CART_NOT_EMPTY , update)
            apply()
        }
    }

    fun getCartRestaurantID(): String? {
        return sharedPreferences.getString(KEY_USER_CART_RESTAURANT_ID , null)
    }

    fun getCartRestaurantName(): String? {
        return sharedPreferences.getString(KEY_USER_CART_RESTAURANT_NAME , null)
    }

    fun updateCartRestaurant(name : String? , ID: String?){
        with(sharedPreferences.edit()) {
            putString(KEY_USER_CART_RESTAURANT_NAME , name)
            putString(KEY_USER_CART_RESTAURANT_ID , ID)
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
        private const val KEY_USER_CART_NOT_EMPTY = "cartNotEmpty"
        private const val KEY_USER_CART_RESTAURANT_ID = "cartRestaurantID"
        private const val KEY_USER_CART_RESTAURANT_NAME = "cartRestaurantName"
        private const val KEY_USER_CART_ADR = "cartAddress"

    }
}