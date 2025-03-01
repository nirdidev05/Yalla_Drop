package com.example.yalladrop.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "addresses")
data class Address(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val address: String,
    val name: String,
    //val state: String,
    //val zipCode: String,
    val isDefault: Boolean = false,

    )