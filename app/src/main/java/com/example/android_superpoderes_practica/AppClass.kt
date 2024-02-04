package com.example.android_superpoderes_practica

import android.app.Application
import com.example.android_superpoderes_practica.data.local.preferences.PrefRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppClass: Application() {

    companion object{
        lateinit var prefRepository: PrefRepository
    }

    override fun onCreate() {
        super.onCreate()
        prefRepository = PrefRepository(applicationContext)
    }
}