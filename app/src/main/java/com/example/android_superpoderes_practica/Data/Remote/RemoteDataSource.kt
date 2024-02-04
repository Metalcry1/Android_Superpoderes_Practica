package com.example.android_superpoderes_practica.Data.Remote


import android.util.Log
import com.example.android_superpoderes_practica.Data.mappers.HeroToHeroRemote
import com.example.android_superpoderes_practica.Domain.Model.HeroRemote
import com.example.android_superpoderes_practica.di.NetworkLogin
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: MarvelApi) {

    val networkLogin = NetworkLogin()

    private val heroToHeroRemote=  HeroToHeroRemote()

    suspend fun getHeroList(offset: Int): List<HeroRemote> {

            val herosResponse = api.getHeros(offset)
            val listHeros = herosResponse.data.results
            val heros = heroToHeroRemote.map(listHeros)

        return heros
    }


    suspend fun launchLogin(userName: String, password: String): String {
        var token = networkLogin.launchLogin(userName, password)
        if (token.isEmpty()){
            return "Respository RemoteDataSource Vacio"
        }else{
            return token
        }
    }

}



