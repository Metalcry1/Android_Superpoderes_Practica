package com.example.android_superpoderes_practica.di

import android.util.Log
import com.example.android_superpoderes_practica.AppClass.Companion.prefRepository
import com.example.android_superpoderes_practica.ui.Login.LoginState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.delay
import okhttp3.Credentials
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class NetworkLogin @Inject constructor(){

    private val token = "eyJraWQiOiJwcml2YXRlIiwiYWxnIjoiSFMyNTYiLCJ0eXAiOiJKV1QifQ.eyJlbWFpbCI6Im1ldGFsY3J5MUBnbWFpbC5jb20iLCJpZGVudGlmeSI6IkJGMUEwMTlDLUJGQUYtNDM4Ri05MEZELTc4RDY3QkQ5M0RCNyIsImV4cGlyYXRpb24iOjY0MDkyMjExMjAwfQ.oeur0LqkLX-K0-n6i-WGGCAYWlWdQiVQ1xRVQRVfJ_Y"

    @Provides
    fun provideNetworkLogin(): NetworkLogin {
        return NetworkLogin()
    }

    @Provides
    suspend fun launchLogin(emailField: String, passWord: String): String {

        delay(1000)

        if (emailField.isNotEmpty() && passWord.isNotEmpty()) {
            return token
        } else {
            throw Exception("Credenciales inválidas. Por favor, intenta de nuevo.")
        }
    }



}

