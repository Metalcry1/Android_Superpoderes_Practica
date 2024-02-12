package com.example.android_superpoderes_practica.Domain.Model


data class MarvelComicsRemote(
        val id: Long,
        val title: String,
        val convertThumbnailComicsToString: String,
        val modified: String?,
    )

data class ComicUI(
    val id: Long,
    val title: String,
    val thumbnailComics: String,
    val modified: String?
)