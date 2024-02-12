package com.example.android_superpoderes_practica.dataa.mappers.HerosMappers

import com.example.android_superpoderes_practica.Domain.Model.Hero
import com.example.android_superpoderes_practica.Domain.Model.HeroRemoteDetail
import com.example.android_superpoderes_practica.Domain.Model.Thumbnail
import javax.inject.Inject

class HeroToHeroRemoteDetail @Inject constructor() {

    fun map(hero: Hero): HeroRemoteDetail {
        return HeroRemoteDetail(
            hero.id,
            hero.name,
            hero.modified,
            convertThumbnailToString(hero.thumbnail),
            hero.comics,
            hero.series
        )
    }
}

private fun convertThumbnailToString(thumbnail: Thumbnail): String {
    val thumbnail = "${thumbnail.path}.${thumbnail.extension}"
    return thumbnail.lowercase()
}
