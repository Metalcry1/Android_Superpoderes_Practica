package com.example.android_superpoderes_practica.Data.mappers.HerosMappers

import com.example.android_superpoderes_practica.Domain.Model.HeroRemoteDetail
import com.example.android_superpoderes_practica.Domain.Model.HeroUIDetail
import javax.inject.Inject

class HeroRemoteDetailToHeroUIDetail @Inject constructor()  {

    fun map(listHero: List<HeroRemoteDetail>): List<HeroUIDetail>{
        return listHero.map {
            HeroUIDetail(
                it.id,
                it.name,
                it.modified,
                it.convertThumbnailToString,
                it.comics,
                it.series
            )
        }
    }

}