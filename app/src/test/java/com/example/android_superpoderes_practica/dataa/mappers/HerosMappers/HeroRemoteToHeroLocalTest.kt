package com.example.android_superpoderes_practica.dataa.mappers.HerosMappers

import com.example.android_superpoderes_practica.Domain.Model.Comics
import com.example.android_superpoderes_practica.Domain.Model.ComicsItem
import com.example.android_superpoderes_practica.Domain.Model.Extensions
import com.example.android_superpoderes_practica.Domain.Model.Hero
import com.example.android_superpoderes_practica.Domain.Model.HeroRemote
import com.example.android_superpoderes_practica.Domain.Model.Thumbnail
import org.junit.Assert.*
import org.junit.Test

class HeroRemoteToHeroLocalTest{

    //SUT

    private val heroRemoteToHeroLocal = HeroRemoteToHeroLocal()

    @Test
    fun `map list of heroRemote to list of heroLocal`() {
        // Given
        val heroRemote =
            HeroRemote(
                id = 1,
                name = "Heroe",
                modified = "2022-01-01",
                convertThumbnailToString = "paginaweb",
                favourite = true
            )

        // When
        val result = heroRemoteToHeroLocal.map(listOf(heroRemote))

        // Then
        assertEquals(1, result.size)

        assertEquals(1, result[0].id)
        assertEquals("Heroe", result[0].name)
        assertEquals("2022-01-01", result[0].modified)
        assertEquals("paginaweb", result[0].thumbnail)
        assertEquals(false, result[0].favourite)

    }
}