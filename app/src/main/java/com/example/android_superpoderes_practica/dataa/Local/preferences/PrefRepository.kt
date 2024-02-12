package com.example.android_superpoderes_practica.data.local.preferences

import android.content.Context

class PrefRepository(val context: Context) {

    val SHARED_NAME = "Mydtb"
    val SHARE_TOKEN = "Token"

    private val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveTokenPreferences(token: String) {
        storage.edit().putString(SHARE_TOKEN, token).apply()
    }

    fun loadTokenPreferences(): String {
        return storage.getString(SHARE_TOKEN, "")!!
    }


}