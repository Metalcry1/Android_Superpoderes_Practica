package com.example.android_superpoderes_practica.dataa.mappers.HerosMappers

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
                it.modified,
                convertThumbnailToString(it.thumbnail),
                it.favourite?: false
            )
        }
    }

    private fun convertThumbnailToString(thumbnail: Thumbnail): String {
        val thumbnail = "${thumbnail.path}.${thumbnail.extensions}"
        return thumbnail.lowercase()
    }
}