package com.example.android_superpoderes_practica.dataa.mappers.HerosMappers

import com.example.android_superpoderes_practica.Domain.Model.Comics
import com.example.android_superpoderes_practica.Domain.Model.ComicsItem
import com.example.android_superpoderes_practica.Domain.Model.Extensions
import com.example.android_superpoderes_practica.Domain.Model.Hero
import com.example.android_superpoderes_practica.Domain.Model.HeroRemoteDetail
import com.example.android_superpoderes_practica.Domain.Model.Thumbnail
import org.junit.Assert.*
import org.junit.Test

class HeroRemoteDetailToHeroUIDetailTest{

    //SUT
    private val heroRemoteDetailToHeroUIDetail = HeroRemoteDetailToHeroUIDetail()

    @Test
    fun `map HeroRemoteDetail to HeroUIDetail`() {
        // Given
        val hero = HeroRemoteDetail(
            id = 123,
            name = "Iron Man",
            modified = "2022-02-10T15:00:00",
            thumbnail = "paginaweb",
            comics = Comics("5", "collect", listOf(ComicsItem("resor", "item"))),
            series = Comics("5", "collect", listOf(ComicsItem("resor", "item"))),
        )

        // When
        val result = heroRemoteDetailToHeroUIDetail.map(hero)

        // Then
        assertEquals(hero.id, result.id)
        assertEquals(hero.name, result.name)
        assertEquals(hero.thumbnail, result.convertThumbnailToString)
        assertEquals(hero.modified, result.modified)
        assertEquals(hero.comics, result.comics)
        assertEquals(hero.series, result.series)
    }
}