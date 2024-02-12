package com.example.android_superpoderes_practica.Domain.Model

import androidx.room.Entity
import com.squareup.moshi.Json

data class MarvelResponseComics(
    @Json(name = "code") val code: Long,
    @Json(name = "status") val status: String,
    @Json(name = "copyright") val copyright: String,
    @Json(name = "attributionText") val attributionText: String,
    @Json(name = "attributionHTML") val attributionHTML: String,
    @Json(name = "etag") val etag: String,
    @Json(name = "data") val dataComics: DataComics
)

data class DataComics(
    @Json(name = "offset") val offset: Long? = null,
    @Json(name = "limit") val limit: Long? = null,
    @Json(name = "total") val total: Long? = null,
    @Json(name = "count") val count: Long? = null,
    @Json(name = "results") val results: List<MarvelComics>
)

data class MarvelComics(
    @Json(name = "id") val id: Long,
    @Json(name = "digitalID") val digitalID: Long? = null,
    @Json(name = "title") val title: String,
    @Json(name = "issueNumber") val issueNumber: Long? = null,
    @Json(name = "variantDescription") val variantDescription: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "modified") val modified: String? = null,
    @Json(name = "isbn") val isbn: String? = null,
    @Json(name = "upc") val upc: String? = null,
    @Json(name = "diamondCode") val diamondCode: String? = null,
    @Json(name = "ean") val ean: String? = null,
    @Json(name = "issn") val issn: String? = null,
    @Json(name = "format") val format: String? = null,
    @Json(name = "pageCount") val pageCount: Long? = null,
    @Json(name = "textObjects") val textObjects: List<TextObject>? = null,
    @Json(name = "resourceURI") val resourceURI: String,
    @Json(name = "urls") val urls: List<URLComics>? = null,
    @Json(name = "series") val series: SeriesComis? = null,
    @Json(name = "variants") val variants: List<SeriesComis>? = null,
    @Json(name = "collections") val collections: List<Any?>? = null,
    @Json(name = "collectedIssues") val collectedIssues: List<SeriesComis>? = null,
    @Json(name = "dates") val dates: List<DateComics>? = null,
    @Json(name = "prices") val prices: List<Price>? = null,
    @Json(name = "thumbnail") val thumbnailComics: ThumbnailComics,
    @Json(name = "images") val images: List<ThumbnailComics>? = null,
    @Json(name = "creators") val creators: CreatorsComics? = null,
    @Json(name = "characters") val characters: CharactersComics? = null,
    @Json(name = "stories") val stories: StoriesComics? = null,
    @Json(name = "events") val events: CharactersComics? = null
)


data class CharactersComics(
    @Json(name = "available") val available: Long? = null,
    @Json(name = "collectionURI") val collectionURI: String? = null,
    @Json(name = "items") val items: List<SeriesComis>? = null,
    @Json(name = "returned") val returned: Long? = null
)

data class SeriesComis(
    @Json(name = "resourceURI") val resourceURI: String? = null,
    @Json(name = "name") val name: String? = null
)

data class CreatorsComics(
    @Json(name = "available") val available: Long? = null,
    @Json(name = "collectionURI") val collectionURI: String? = null,
    @Json(name = "items") val items: List<CreatorsItem>? = null,
    @Json(name = "returned") val returned: Long? = null
)

data class CreatorsItem(
    @Json(name = "resourceURI") val resourceURI: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "role") val role: String? = null
)

enum class Role {
    colorist,
    editor,
    inker,
    letterer,
    penciler,
    penciller,
    pencillerCover,
    writer
}

data class DateComics(
    @Json(name = "type") val type: DateType? = null,
    @Json(name = "date") val date: String? = null
)

enum class DateType {
    digitalPurchaseDate,
    focDate,
    onsaleDate,
    unlimitedDate
}

enum class Description {
    empty,
    NA
}

data class DiamondCode(
    @Json(name = "JUL190068") val JUL190068: List<String>? = null
)

enum class Format {
    comic,
    digest,
    empty,
    tradePaperback,
}

data class ThumbnailComics(
    @Json(name = "path") val path: String? = null,
    @Json(name = "extension") val extension: ExtensionComics? = null
)

enum class ExtensionComics {
    @Json(name = "gif")
    gif,
    @Json(name = "jpg")
    jpg
}

enum class Isbn {
    empty,
    the0785111298,
    the0785114513,
    the0785115609
}

data class Price(
    @Json(name = "type") val type: PriceType? = null,
    @Json(name = "price") val price: Double? = null
)

enum class PriceType {
    digitalPurchasePrice,
    printPrice
}

data class StoriesComics(
    @Json(name = "available") val available: Long? = null,
    @Json(name = "collectionURI") val collectionURI: String? = null,
    @Json(name = "items") val items: List<StoriesItemComics>? = null,
    @Json(name = "returned") val returned: Long? = null
)

data class StoriesItemComics(
    @Json(name = "resourceURI") val resourceURI: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "type") val type: String? = null
)


data class ItemTypeComics (
    @Json(name = "cover") val cover: String? = null,
    @Json(name = "interiorStory") val interiorStory:String? = null,
    @Json(name = "promo") val promo: String? = null,
)

data class TextObject(
    @Json(name = "type") val type: String? = null,
    @Json(name = "language") val language: String? = null,
    @Json(name = "text") val text: String? = null
)


data class Language(
    val enus: String? = null,
)

enum class TextObjectType {
    issue_preview_text,
    issue_solicit_text
}


data class URLComics(
    @Json(name = "type") val type: URLTypeComics? = null,
    @Json(name = "url") val url: String? = null
)

enum class URLTypeComics {
    detail,
    inAppLink,
    purchase,
    reader
}
