package com.example.android_superpoderes_practica.dataa.mappers.HerosMappers


import com.example.android_superpoderes_practica.Domain.Model.HeroLocal
import com.example.android_superpoderes_practica.Domain.Model.HeroUI
import javax.inject.Inject

class HeroLocalToUIMapper @Inject constructor() {

    fun map(localHeros: List<HeroLocal>): List<HeroUI>{
        return localHeros.map {
            HeroUI(
                it.id,
                it.name,
                it.thumbnail,
                it.favourite
                ) }
    }
}
