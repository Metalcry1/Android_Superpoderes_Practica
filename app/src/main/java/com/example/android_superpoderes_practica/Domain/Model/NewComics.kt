package com.example.android_superpoderes_practica.Domain.Model

import androidx.room.Entity


    data class MarvelComicsRemote (
        val id: Long,
        val title: String,
        val convertThumbnailComicsToString: String,
    )

data class ComicUI (
    val id: Long,
    val title: String,
    val thumbnailComics: String
)