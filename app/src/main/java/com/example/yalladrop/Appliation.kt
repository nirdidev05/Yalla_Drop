package com.example.yalladrop

import android.app.Application
import com.example.yalladrop.local.AddressDatabase

class YallaDrop : Application() {
    val database: AddressDatabase by lazy { AddressDatabase.getDatabase(this) }
}