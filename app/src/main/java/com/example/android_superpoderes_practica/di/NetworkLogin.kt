package com.example.android_superpoderes_practica.di

import android.util.Log
import com.example.android_superpoderes_practica.AppClass.Companion.prefRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Credentials
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


@Module

@InstallIn(SingletonComponent::class)
class NetworkLogin {

    private val BASE_URL = "https://dragonball.keepcoding.education/api/"
    private var token : String = ""


    @Provides
    fun launchLogin(emailField: String, passWord: String): String {
        var token = ""

        val thread = Thread {
            val client = OkHttpClient()
            val url = "${BASE_URL}auth/login"
            val credentials = Credentials.basic(emailField, passWord)
            val formBody = FormBody.Builder().build()

            val request = Request.Builder()
                .url(url)
                .addHeader("Authorization", credentials)
                .post(formBody)
                .build()

            val call = client.newCall(request)
            val response = call.execute()

            if (response.isSuccessful) {
                response.body?.let {
                    token = it.string()
                }
            }
        }

        thread.start()
        thread.join()

        return token
    }



}

