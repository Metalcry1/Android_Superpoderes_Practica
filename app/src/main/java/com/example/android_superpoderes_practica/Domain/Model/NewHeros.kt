package com.example.android_superpoderes_practica.Domain.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heros")
data class HeroLocal (
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "modified")val modified: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "favourite") val favourite: Boolean = false
)


data class HeroRemote(
    val id: Long,
    val name: String,
    val modified: String,
    val convertThumbnailToString: String,
    val favourite: Boolean? = false
)

data class HeroRemoteDetail(
    val id: Long,
    val name: String,
    val modified: String,
    val thumbnail: String,
    val comics: Comics,
    val series: Comics,
)

data class HeroUIDetail(
    val id: Long,
    val name: String,
    val modified: String,
    val convertThumbnailToString: String,
    val comics: Comics,
    val series: Comics,
)

data class HeroUI(
    val id: Long,
    val name: String,
    val thumbnail: String,
    val favourite: Boolean
)


