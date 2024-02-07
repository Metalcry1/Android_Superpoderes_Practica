package com.example.android_superpoderes_practica.Data.Remote



import com.example.android_superpoderes_practica.Domain.Model.Marvel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("characters")
    suspend fun getHeros(@Query("offset") offset: Int): Marvel


    @GET("characters/{id}")
    suspend fun getOneHero(@Path("id") id: Int): Marvel

}

//https://gateway.marvel.com:443/v1/public/characters/1010699?apikey=f3603d6d0330a129071bf9dfa05b49ca