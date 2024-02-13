package com.example.android_superpoderes_practica.dataa.mappers.HerosMappers

import com.example.android_superpoderes_practica.Domain.Model.HeroLocal
import com.example.android_superpoderes_practica.Domain.Model.HeroRemote
import javax.inject.Inject

class HeroRemoteToHeroLocal @Inject constructor()  {

    fun map(listHeros: List<HeroRemote>): List<HeroLocal>{
        return listHeros.map {
            HeroLocal(
                it.id,
                it.name,
                it.modified,
                it.convertThumbnailToString,



            )
        }
    }
}