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


data class HeroRemote(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    val convertThumbnailToString: String,
)

data class HeroRemoteDetail(
    val id: Long,
    val name: String,
    val description: String,
    val modified: String,
    val convertThumbnailToString: String,
    val comics: Comics,
    val series: Comics,

    )


data class HeroUI(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: String
)

data class HeroUIDetail(
    val id: Long,
    val name: String,
    val description: String,
    val modified: String,
    val convertThumbnailToString: String,
    val comics: Comics,
    val series: Comics,

    )


