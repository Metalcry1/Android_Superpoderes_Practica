package com.example.android_superpoderes_practica.data.local


import com.example.android_superpoderes_practica.Domain.Model.HeroLocal
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val dao: HeroDAO): LocalDataSourceInterface {

    override fun getHeros(): List<HeroLocal> {
        return dao.getAll()
    }

    override fun insertHerosToDB(heros: List<HeroLocal>) {
        dao.insertAll(heros)
    }

    override fun updateHeroestoDB(heros: List<HeroLocal>){
        dao.updateHeroes(heros)
    }

    override fun updateFavoriteStatus(heroId: Long, isFavorite: Boolean){
        dao.updateFavoriteStatus(heroId,isFavorite)
    }

    override fun getHeroStatusFavourite(heroId: Long): Boolean {
        return dao.getHeroStatusFavourite(heroId)
    }

}