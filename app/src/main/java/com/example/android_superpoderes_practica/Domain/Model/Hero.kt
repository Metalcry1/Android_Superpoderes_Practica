package com.example.android_superpoderes_practica.Domain.Model

import androidx.room.Entity
import com.squareup.moshi.Json

@Entity(tableName = "heros")
data class Marvel(
    @Json(name = "code") val code: String,
    @Json(name = "status") val status: String,
    @Json(name = "copyright") val copyright: String,
    @Json(name = "attributionText") val attributionText: String,
    @Json(name = "attributionHTML") val attributionHTML: String,
    @Json(name = "etag") val etag: String,
    @Json(name = "data") val data: Data
)

data class Data(
    @Json(name = "offset") val offset: String,
    @Json(name = "limit") val limit: String,
    @Json(name = "total") val total: String,
    @Json(name = "count") val count: String,
    @Json(name = "results") val results: List<Hero>
)

data class Hero(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String,
    @Json(name = "modified") val modified: String,
    @Json(name = "thumbnail") val thumbnail: Thumbnail,
    @Json(name = "resourceURI") val resourceURI: String,
    @Json(name = "comics") val comics: Comics,
    @Json(name = "series") val series: Comics,
    @Json(name = "stories") val stories: Stories,
    @Json(name = "events") val events: Comics,
    @Json(name = "urls") val urls: List<URL>
)

data class Comics(
    @Json(name = "available") val available: String,
    @Json(name = "collectionURI") val collectionURI: String,
    @Json(name = "items") val items: List<ComicsItem>,
    @Json(name = "returned") val returned: String
)

data class ComicsItem(
    @Json(name = "resourceURI") val resourceURI: String,
    @Json(name = "name") val name: String
)

data class Stories(
    @Json(name = "available") val available: String,
    @Json(name = "collectionURI") val collectionURI: String,
    @Json(name = "items") val items: List<StoriesItem>,
    @Json(name = "returned") val returned: String
)

data class StoriesItem(
    @Json(name = "resourceURI") val resourceURI: String,
    @Json(name = "name") val name: String,
    @Json(name = "type") val type: ItemType
)

enum class ItemType {
    @Json(name = "cover") Cover,
    @Json(name = "") Empty,
    @Json(name = "interiorStory") InteriorStory
}

data class Thumbnail(
    @Json(name = "path") val path: String,
    @Json(name = "extension") val extension: Extension
)

enum class Extension {
    @Json(name = "gif") GIF,
    @Json(name = "jpg") Jpg
}

data class URL(
    @Json(name = "type") val type: URLType,
    @Json(name = "url") val url: String
)

enum class URLType {
    @Json(name = "comiclink") Comiclink,
    @Json(name = "detail") Detail,
    @Json(name = "wiki") Wiki
}
