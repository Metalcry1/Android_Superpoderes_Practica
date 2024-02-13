package com.example.android_superpoderes_practica.dataa.mappers.HerosMappers

import com.example.android_superpoderes_practica.Domain.Model.*
import org.junit.Assert.assertEquals
import org.junit.Test

class HeroToHeroRemoteTest {

    private val heroToHeroRemote = HeroToHeroRemote()

    @Test
    fun `map list of heroes to list of hero remotes`() {
        // Given
        val heroes = listOf(
            Hero(
                id = 1,
                name = "Heroe",
                modified = "2022-01-01",
                thumbnail = Thumbnail("paginaweb", Extensions.jpg),
                comics = Comics("5", "collect", listOf(ComicsItem("resor", "item"))),
                series = Comics("5", "collect", listOf(ComicsItem("resor", "item"))),
                favourite = true
            ),
            Hero(
                id = 2,
                name = "Heroe",
                modified = "2022-01-02",
                thumbnail = Thumbnail("paginaweb", Extensions.jpg),
                comics = Comics("5", "collect", listOf(ComicsItem("resor", "item"))),
                series = Comics("5", "collect", listOf(ComicsItem("resor", "item"))),
                favourite = false
            )
        )

        // When
        val result = heroToHeroRemote.map(heroes)

        // Then
        assertEquals(2, result.size)

        assertEquals(1, result[0].id)
        assertEquals("Heroe", result[0].name)
        assertEquals("2022-01-01", result[0].modified)
        assertEquals("paginaweb.jpg", result[0].convertThumbnailToString)
        assertEquals(true, result[0].favourite)

        assertEquals(2, result[1].id)
        assertEquals("Heroe", result[1].name)
        assertEquals("2022-01-02", result[1].modified)
        assertEquals("paginaweb.jpg", result[1].convertThumbnailToString)
        assertEquals(false, result[1].favourite)
    }
}
