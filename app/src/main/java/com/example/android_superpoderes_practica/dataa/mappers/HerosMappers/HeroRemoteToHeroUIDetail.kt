package com.example.android_superpoderes_practica.dataa.mappers.HerosMappers

import com.example.android_superpoderes_practica.Domain.Model.Hero
import com.example.android_superpoderes_practica.Domain.Model.HeroRemote
import com.example.android_superpoderes_practica.Domain.Model.HeroRemoteDetail
import com.example.android_superpoderes_practica.Domain.Model.HeroUIDetail
import com.example.android_superpoderes_practica.Domain.Model.Thumbnail
import javax.inject.Inject

class HeroRemoteDetailToHeroUIDetail @Inject constructor() {

    fun map(hero: HeroRemoteDetail): HeroUIDetail {
        return HeroUIDetail(
            hero.id,
            hero.name,
            hero.modified,
            hero.thumbnail,
            hero.comics,
            hero.series
        )
    }

}
