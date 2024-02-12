package com.example.android_superpoderes_practica.dataa.Remote

import com.example.android_superpoderes_practica.dataa.mappers.ComicMappers.ComicRemoteToComicUI
import com.example.android_superpoderes_practica.dataa.mappers.HerosMappers.HeroLocalToUIMapper
import com.example.android_superpoderes_practica.dataa.mappers.HerosMappers.HeroRemoteDetailToHeroUIDetail
import com.example.android_superpoderes_practica.dataa.mappers.HerosMappers.HeroRemoteToHeroLocal
import com.example.android_superpoderes_practica.data.local.LocalDataSourceInterface
import com.example.android_superpoderes_practica.Domain.Model.HeroLocal
import com.example.android_superpoderes_practica.Domain.Model.HeroRemote
import com.example.android_superpoderes_practica.Domain.Model.HeroUI
import com.example.android_superpoderes_practica.Domain.Model.HeroUIDetail
import com.example.android_superpoderes_practica.Domain.Model.ComicUI
import com.example.android_superpoderes_practica.Domain.Model.HeroRemoteDetail
import com.example.android_superpoderes_practica.Domain.Model.MarvelComicsRemote
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: LocalDataSourceInterface,
    private val remoteDataSource: RemoteDataSource,
    private val localToUIMapper: HeroLocalToUIMapper,
    private val heroRemoteToHeroLocal: HeroRemoteToHeroLocal,
    private val heroRemoteDetailToHeroUIDetail: HeroRemoteDetailToHeroUIDetail,
    private val comicRemoteToComicUI: ComicRemoteToComicUI
) {


    fun getHeroList(offset: Int): Flow<List<HeroUI>> {

        val localHeros: List<HeroLocal> = localDataSource.getHeros()

        if (localHeros.isEmpty()) {

            return getHeroListToRemote(offset)

        } else {

            return getHeroListToDB(localHeros)
        }

    }

    private fun getHeroListToDB(localHeros: List<HeroLocal>): Flow<List<HeroUI>> = flow {

        emit(localToUIMapper.map(localHeros))

    }

    private fun getHeroListToRemote(offset: Int): Flow<List<HeroUI>> = flow {

        val remoteHeros: List<HeroRemote> = remoteDataSource.getHeroList(offset)
        val heroConvertToLocalClass = heroRemoteToHeroLocal.map(remoteHeros)
        localDataSource.insertHerosToDB(heroConvertToLocalClass)
        val updatedLocalHeros: List<HeroLocal> = localDataSource.getHeros()
        emit(localToUIMapper.map(updatedLocalHeros))

    }

    fun getComicsListToRemote(offset: Int): Flow<List<ComicUI>> = flow {

        val comicRemote: List<MarvelComicsRemote> = remoteDataSource.getComicList(offset)
        val comicRemoteConvertToUI = comicRemoteToComicUI.map(comicRemote)
        emit(comicRemoteConvertToUI)

    }


    suspend fun launchLogin(userName: String, password: String): String {
        var token = remoteDataSource.launchLogin(userName, password)

        if (token.isEmpty()) {
            return "Respository vacio"
        } else {
            return token
        }
    }

    suspend fun insertMoreHeroes(offset: Int) {
        val remoteHeros: List<HeroRemote> = remoteDataSource.getHeroList(offset)
        val heroConvertToLocalClass = heroRemoteToHeroLocal.map(remoteHeros)
        localDataSource.updateHeroestoDB(heroConvertToLocalClass)

    }


    fun getOneHeroListToRemote(id: String): Flow<HeroUIDetail> = flow {
        val idString = id
        val idLong: Long = idString.toLong()
        val heroRemoteDetail: HeroRemoteDetail = remoteDataSource.getOneHero(idLong)

        val heroConvertToUIDetail = heroRemoteDetailToHeroUIDetail.map(heroRemoteDetail)

        emit(heroConvertToUIDetail)

    }

    fun updateFavoriteStatus(heroId: Long, isFavorite: Boolean) {
        localDataSource.updateFavoriteStatus(heroId, isFavorite)
    }

     fun getHeroStatusFavourite(heroId: Long): Boolean {
         return localDataSource.getHeroStatusFavourite(heroId)
     }

}

