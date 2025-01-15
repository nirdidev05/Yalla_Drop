package com.example.yalladrop

import android.app.Application
import com.example.yalladrop.local.AppDataBase

class YallaDrop : Application() {
    val database: AppDataBase by lazy { AppDataBase.getDatabase(this) }
}