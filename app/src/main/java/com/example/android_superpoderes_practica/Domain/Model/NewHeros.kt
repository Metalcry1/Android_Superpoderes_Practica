package com.example.android_superpoderes_practica.Domain.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "heros")
data class HeroLocal (
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
)

/*
data class HeroRemote(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "modified") val modified: String,
    @Json(name = "thumbnail") val thumbnail: String,
    @Json(name = "resourceURI") val resourceURI: String,
    @Json(name = "comics") val comics: Comics,
    @Json(name = "series") val series: Comics,
    @Json(name = "stories") val stories: Stories,
    @Json(name = "events") val events: Comics,
    @Json(name = "urls") val urls: List<URL>
)

 */

data class HeroRemote(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    val convertThumbnailToString: String,
)



data class HeroUI(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: String
)