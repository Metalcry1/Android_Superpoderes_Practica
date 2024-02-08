package com.example.android_superpoderes_practica.Data.Remote


import android.util.Log
import com.example.android_superpoderes_practica.Data.mappers.ComicMappers.ComicToComicRemote
import com.example.android_superpoderes_practica.Data.mappers.HerosMappers.HeroToHeroRemote
import com.example.android_superpoderes_practica.Data.mappers.HerosMappers.HeroToHeroRemoteDetail
import com.example.android_superpoderes_practica.Domain.Model.HeroRemote
import com.example.android_superpoderes_practica.Domain.Model.HeroRemoteDetail
import com.example.android_superpoderes_practica.Domain.Model.MarvelComicsRemote
import com.example.android_superpoderes_practica.di.NetworkLogin
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: MarvelApi,
    private val heroToHeroRemote: HeroToHeroRemote,
    private val heroToHeroRemoteDetail: HeroToHeroRemoteDetail,
    private val comicToComicRemote: ComicToComicRemote
) {

    val networkLogin = NetworkLogin()


    suspend fun getHeroList(offset: Int): List<HeroRemote> {

            val herosResponse = api.getHeros(offset)
            val listHeros = herosResponse.data.results
            val heros = heroToHeroRemote.map(listHeros)

        return heros
    }

    suspend fun getOneHero(id: Int?): List<HeroRemoteDetail>{

        val id: Int = id ?: 0

        val heroResponse = api.getOneHero(id)
        val hero = heroResponse.data.results
        Log.w("HEROES REMOTE", "GETONE ${hero.toString()}")
        val heroRemote = heroToHeroRemoteDetail.map(hero)

        return heroRemote
    }

    suspend fun getComicList(offset: Int): List<MarvelComicsRemote>{
        val comicsResponse = api.getComics(offset)
        val comics = comicsResponse.dataComics.results
        val comicsRemote = comicToComicRemote.map(comics)
        return comicsRemote
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



