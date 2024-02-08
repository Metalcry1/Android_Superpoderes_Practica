package com.example.android_superpoderes_practica.Data.mappers.HerosMappers

import com.example.android_superpoderes_practica.Domain.Model.Hero
import com.example.android_superpoderes_practica.Domain.Model.HeroRemoteDetail
import com.example.android_superpoderes_practica.Domain.Model.Thumbnail
import javax.inject.Inject

class HeroToHeroRemoteDetail @Inject constructor() {

    fun map(listHero: List<Hero>): List<HeroRemoteDetail> {
        return listHero.map {
            HeroRemoteDetail(
                it.id,
                it.name,
                it.modified,
                convertThumbnailToString(it.thumbnail),
                it.comics,
                it.series


            )
        }
    }

    private fun convertThumbnailToString(thumbnail: Thumbnail): String {
        val thumbnail = "${thumbnail.path}.${thumbnail.extension}"
        return thumbnail.lowercase()
    }
}