package com.example.android_superpoderes_practica.Data.Remote

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface DragonBallApiLogin {

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Header("Authorization") credentials: String
    ): String

}