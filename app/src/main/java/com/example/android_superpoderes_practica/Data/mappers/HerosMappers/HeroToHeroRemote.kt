package com.example.android_superpoderes_practica.Data.mappers.HerosMappers

import com.example.android_superpoderes_practica.Domain.Model.Hero
import com.example.android_superpoderes_practica.Domain.Model.HeroRemote
import com.example.android_superpoderes_practica.Domain.Model.Thumbnail
import javax.inject.Inject

class HeroToHeroRemote @Inject constructor() {

    fun map(listHeros: List<Hero>): List<HeroRemote> {
        return listHeros.map {
            HeroRemote(
                it.id,
                it.name,
                convertThumbnailToString(it.thumbnail)//
            )
        }
    }

    private fun convertThumbnailToString(thumbnail: Thumbnail): String {
        val thumbnail = "${thumbnail.path}.${thumbnail.extension}"
        return thumbnail.lowercase()
    }
}