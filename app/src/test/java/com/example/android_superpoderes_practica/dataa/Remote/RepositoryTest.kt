package com.example.android_superpoderes_practica.dataa.Remote

import com.example.android_superpoderes_practica.Domain.Model.ComicUI
import com.example.android_superpoderes_practica.Domain.Model.HeroLocal
import com.example.android_superpoderes_practica.Domain.Model.HeroRemote
import com.example.android_superpoderes_practica.Domain.Model.HeroUI
import com.example.android_superpoderes_practica.Domain.Model.MarvelComicsRemote
import com.example.android_superpoderes_practica.data.local.LocalDataSource
import com.example.android_superpoderes_practica.dataa.Remote.RemoteDataSource
import com.example.android_superpoderes_practica.dataa.mappers.ComicMappers.ComicRemoteToComicUI
import com.example.android_superpoderes_practica.dataa.mappers.HerosMappers.HeroLocalToUIMapper
import com.example.android_superpoderes_practica.dataa.mappers.HerosMappers.HeroRemoteDetailToHeroUIDetail
import com.example.android_superpoderes_practica.dataa.mappers.HerosMappers.HeroRemoteToHeroLocal
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk

import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RepositoryTest {


    // SUT
    private lateinit var repository: Repository

    //Dependencias
    private val localDataSource: LocalDataSource = mockk()
    private val remoteDataSource: RemoteDataSource = mockk()
    private val heroLocalToUIMapper = HeroLocalToUIMapper()
    private val heroRemoteToHeroLocal: HeroRemoteToHeroLocal = mockk()
    private val heroRemoteDetailToHeroUIDetail: HeroRemoteDetailToHeroUIDetail = mockk()
    private val comicRemoteToComicUI: ComicRemoteToComicUI = mockk()

    val offset = 0
    val remoteHeroes = listOf(
        HeroRemote(1, "Iron Man", "2024-02-10T15:00:00", "http://example.com/ironman.jpg"),
        HeroRemote(
            2,
            "Spider-Man",
            "2024-02-10T15:00:00",
            "http://example.com/spiderman.jpg"
        )
    )
    val localHeroes = listOf(
        HeroLocal(1, "Iron Man", "2024-02-10T15:00:00", "http://example.com/ironman.jpg"),
        HeroLocal(
            2,
            "Spider-Man",
            "2024-02-10T15:00:00",
            "http://example.com/spiderman.jpg"
        )
    )
    val heroUI = HeroUI(1, "Iron Man", "http://example.com/ironman.jpg", true)
    val listHeroUI = listOf(heroUI, heroUI)

    val marvelComicsRemote =
        MarvelComicsRemote(1, "Iron Man", "http://example.com/ironman.jpg", "2024-02-10T15:00:00")
    val listMarvelComicsRemote = listOf(marvelComicsRemote, marvelComicsRemote)

    @Before
    fun setUp() {

        repository = Repository(
            localDataSource,
            remoteDataSource,
            heroLocalToUIMapper,
            heroRemoteToHeroLocal,
            heroRemoteDetailToHeroUIDetail,
            comicRemoteToComicUI
        )
    }

    @After
    fun tearDown() {
    }


    @Test
    fun `WHEN getHeroList THEN SUCCESS return list from local and remote with FAKE`() = runTest {

        // Given
        coEvery { localDataSource.getHeros() } returns localHeroes
        coEvery { remoteDataSource.getHeroList(0) } returns remoteHeroes

        // When
        val actual1 = remoteDataSource.getHeroList(offset)
        val actual2 = localDataSource.getHeros()

        // Then
        coVerify { remoteDataSource.getHeroList(0) }
        coVerify { localDataSource.getHeros() }

    }


    @Test
    fun `WHEN getComicList THE SUCCESS return list from remote called with FAKE`() = runTest {

        // Given
        coEvery { remoteDataSource.getComicList(offset) } returns listMarvelComicsRemote

        // When
        val actual = remoteDataSource.getComicList(offset)

        coVerify { remoteDataSource.getComicList(0) }
    }

    /*
    suspend fun launchLogin(userName: String, password: String): String {
    var token = remoteDataSource.launchLogin(userName, password)

    if (token.isEmpty()) {
        return "Respository vacio"
    } else {
        return token
    }
}

}
 */

    @Test
    fun `WHEN launchLogin THE SUCCESS return token String`()= runTest{
        val username = "username@hotmail.com"
        val password = "1237892"
        val token = "wojefklwej¡3423'0"

        //Given
        coEvery { remoteDataSource.launchLogin(username, password) } returns token

        // When
        val actual = remoteDataSource.launchLogin(username, password)

        coVerify { remoteDataSource.launchLogin(username, password)}


    }

}