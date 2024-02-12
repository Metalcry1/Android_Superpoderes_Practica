package com.example.android_superpoderes_practica.data.local

import com.example.android_superpoderes_practica.Domain.Model.HeroLocal
import com.example.android_superpoderes_practica.Domain.Model.HeroRemote


interface LocalDataSourceInterface {
    fun getHeros(): List<HeroLocal>
    fun insertHerosToDB(heros: List<HeroLocal>)
    fun updateHeroestoDB(heros: List<HeroLocal>)
    fun updateFavoriteStatus(heroId: Long, isFavorite: Boolean)
    fun getHeroStatusFavourite(heroId: Long,): Boolean

}