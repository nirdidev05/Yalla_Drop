package com.example.yalladrop

import android.app.Application

class YallaDrop : Application() {
    val database: AddressDatabase by lazy { AddressDatabase.getDatabase(this) }
}