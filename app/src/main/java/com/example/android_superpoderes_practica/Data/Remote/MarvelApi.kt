package com.example.android_superpoderes_practica.Data.Remote



import com.example.android_superpoderes_practica.Domain.Model.Marvel
import com.example.android_superpoderes_practica.Domain.Model.MarvelResponseComics
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("characters?modifiedSince=01%2F01%2F2010&limit=10")
    suspend fun getHeros(@Query("offset") offset: Int): Marvel


    @GET("characters/{id}")
    suspend fun getOneHero(@Path("id") id: Int): Marvel


    @GET("comics?modifiedSince=01%2F01%2F2010")
    suspend fun getComics(@Query("offset") offset: Int): MarvelResponseComics

}

//https://gateway.marvel.com:443/v1/public/characters/1010699?apikey=f3603d6d0330a129071bf9dfa05b49ca