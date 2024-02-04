package com.example.android_superpoderes_practica.data.local

import com.example.android_superpoderes_practica.Domain.Model.HeroLocal


interface LocalDataSourceInterface {
    fun getHeros(): List<HeroLocal>
    fun insertHerosToDB(heros: List<HeroLocal>)
    fun updateHeroestoDB(heros: List<HeroLocal>)
}