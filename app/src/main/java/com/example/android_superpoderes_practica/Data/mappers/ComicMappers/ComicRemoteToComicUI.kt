package com.example.android_superpoderes_practica.Data.mappers.ComicMappers

import com.example.android_superpoderes_practica.Domain.Model.ComicUI
import com.example.android_superpoderes_practica.Domain.Model.MarvelComicsRemote
import javax.inject.Inject

class ComicRemoteToComicUI @Inject constructor()  {

    fun map(listComic: List<MarvelComicsRemote>): List<ComicUI>{
        return listComic.map {
            ComicUI(
                it.id,
                it.title,
                it.convertThumbnailComicsToString,
                it.modified
            )
        }
    }

}