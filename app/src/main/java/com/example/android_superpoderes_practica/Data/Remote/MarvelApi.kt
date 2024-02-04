package com.example.android_superpoderes_practica.Data.Remote



import com.example.android_superpoderes_practica.Domain.Model.Marvel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("characters")
    suspend fun getHeros(@Query("offset") offset: Int): Marvel

}