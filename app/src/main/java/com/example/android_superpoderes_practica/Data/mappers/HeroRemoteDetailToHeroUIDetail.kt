package com.example.android_superpoderes_practica.Data.mappers

import com.example.android_superpoderes_practica.Domain.Model.HeroLocal
import com.example.android_superpoderes_practica.Domain.Model.HeroRemote
import com.example.android_superpoderes_practica.Domain.Model.HeroRemoteDetail
import com.example.android_superpoderes_practica.Domain.Model.HeroUIDetail
import javax.inject.Inject

class HeroRemoteDetailToHeroUIDetail @Inject constructor()  {

    fun map(listHero: List<HeroRemoteDetail>): List<HeroUIDetail>{
        return listHero.map {
            HeroUIDetail(
                it.id,
                it.name,
                it.description,
                it.modified,
                it.convertThumbnailToString,
                it.comics,
                it.series
            )
        }
    }

}