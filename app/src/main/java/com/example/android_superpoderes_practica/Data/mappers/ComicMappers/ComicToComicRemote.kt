package com.example.android_superpoderes_practica.Data.mappers.ComicMappers

import com.example.android_superpoderes_practica.Domain.Model.Hero
import com.example.android_superpoderes_practica.Domain.Model.HeroRemote
import com.example.android_superpoderes_practica.Domain.Model.MarvelComics
import com.example.android_superpoderes_practica.Domain.Model.MarvelComicsRemote
import com.example.android_superpoderes_practica.Domain.Model.Thumbnail
import com.example.android_superpoderes_practica.Domain.Model.ThumbnailComics
import javax.inject.Inject

class ComicToComicRemote @Inject constructor() {

    fun map(listHeros: List<MarvelComics>): List<MarvelComicsRemote> {
        return listHeros.map {
            MarvelComicsRemote(
                it.id,
                it.title,
                convertThumbnailComicsToString(it.thumbnailComics)//
            )
        }
    }

    private fun convertThumbnailComicsToString(thumbnailComics: ThumbnailComics): String {
        val thumbnail = "${thumbnailComics.path}.${thumbnailComics.extension}"
        return thumbnail.lowercase()
    }
}