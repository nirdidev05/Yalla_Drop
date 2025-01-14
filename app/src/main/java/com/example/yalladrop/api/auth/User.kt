package com.example.yalladrop.api.auth

data class User (
    val _id : String ,
    val name : String ,
    val email:String,
    val phone: String,
    val address: String = "My adress",
    val password:  String = "My password",
    val profilePicture: String = "profilePicture",
    val googleId: String  = "googleId" ,
    val isVerified: Boolean = true,
)