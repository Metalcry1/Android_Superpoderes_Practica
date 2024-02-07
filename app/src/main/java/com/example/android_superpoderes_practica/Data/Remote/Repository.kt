package com.example.android_superpoderes_practica.Data.Remote

import android.util.Log
import com.example.android_superpoderes_practica.Data.mappers.HeroRemoteDetailToHeroUIDetail
import com.example.android_superpoderes_practica.Data.mappers.HeroRemoteToHeroLocal
import com.example.android_superpoderes_practica.data.local.LocalDataSourceInterface
import com.example.android_superpoderes_practica.Domain.Model.HeroLocal
import com.example.android_superpoderes_practica.Domain.Model.HeroRemote
import com.example.android_superpoderes_practica.Domain.Model.HeroRemoteDetail
import com.example.android_superpoderes_practica.Domain.Model.HeroUI
import com.example.android_superpoderes_practica.Domain.Model.HeroUIDetail
import com.example.android_superpoderes_practica.data.mappers.LocalToUIMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


import javax.inject.Inject

class Repository @Inject constructor(
    private val localDataSource: LocalDataSourceInterface,
    private val remoteDataSource: RemoteDataSource,
    private val localToUIMapper: LocalToUIMapper,
    private val heroRemoteToHeroLocal: HeroRemoteToHeroLocal,
    private val heroRemoteDetailToHeroUIDetail: HeroRemoteDetailToHeroUIDetail
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

        emit( localToUIMapper.map(localHeros))

    }

     private fun getHeroListToRemote(offset: Int): Flow<List<HeroUI>> = flow {

        val remoteHeros: List<HeroRemote> = remoteDataSource.getHeroList(offset)
        val heroConvertToLocalClass = heroRemoteToHeroLocal.map(remoteHeros)
        localDataSource.insertHerosToDB(heroConvertToLocalClass)
        val updatedLocalHeros: List<HeroLocal> = localDataSource.getHeros()

          emit(localToUIMapper.map(updatedLocalHeros))

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

    suspend fun getOneHeroToRemote(id: Int?){
        remoteDataSource.getOneHero(id)
    }


    /////////////////////////////////////////////////////////////////////////////////

     fun getOneHeroListToRemote(id: Int?): Flow<List<HeroUIDetail>> = flow {

        val remoteHeros: List<HeroRemoteDetail> = remoteDataSource.getOneHero(id)
        val heroConvertToUIDetail = heroRemoteDetailToHeroUIDetail.map(remoteHeros)

        emit(heroConvertToUIDetail)

    }

}

