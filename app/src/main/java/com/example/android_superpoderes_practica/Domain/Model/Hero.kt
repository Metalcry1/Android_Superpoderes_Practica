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
    @Json(name = "offset") val offset: String? = null,
    @Json(name = "limit") val limit: String? = null,
    @Json(name = "total") val total: String? = null,
    @Json(name = "count") val count: String? = null,
    @Json(name = "results") val results: List<Hero>
)

data class Hero(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "description") val description: String? = null,
    @Json(name = "modified") val modified: String,
    @Json(name = "thumbnail") val thumbnail: Thumbnail,
    @Json(name = "resourceURI") val resourceURI: String? = null,
    @Json(name = "comics") val comics: Comics,
    @Json(name = "series") val series: Comics,
    @Json(name = "stories") val stories: Stories? = null,
    @Json(name = "events") val events: Comics? = null,
    @Json(name = "urls") val urls: List<URL>? = null,
    @Json(name = "favourite") val  favourite: Boolean? = null
)

data class Comics(
    @Json(name = "available") val available: String? = null,
    @Json(name = "collectionURI") val collectionURI: String? = null,
    @Json(name = "items") val items: List<ComicsItem>,
    @Json(name = "returned") val returned: String? = null
)

data class ComicsItem(
    @Json(name = "resourceURI") val resourceURI: String? = null,
    @Json(name = "name") val name: String? = null
)

data class Stories(
    @Json(name = "available") val available: String? = null,
    @Json(name = "collectionURI") val collectionURI: String? = null,
    @Json(name = "items") val items: List<StoriesItem>? = null,
    @Json(name = "returned") val returned: String? = null
)

data class StoriesItem(
    @Json(name = "resourceURI") val resourceURI: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "type") val type: String
)

enum class ItemType {
    cover,
    interiorStory
}

data class Thumbnail(
    @Json(name = "path") val path: String? = null,
    @Json(name = "extension") val extension: Extension? = null
)

enum class Extension {
    gif,
    jpg
}

data class URL(
    @Json(name = "type") val type: URLType? = null,
    @Json(name = "url") val url: String? = null
)

enum class URLType {
    comiclink,
    detail,
    wiki
}
