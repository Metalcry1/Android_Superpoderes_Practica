package com.example.android_superpoderes_practica.ui.HeroesDetail

import app.cash.turbine.test
import com.example.android_superpoderes_practica.Domain.Model.Comics
import com.example.android_superpoderes_practica.Domain.Model.ComicsItem
import com.example.android_superpoderes_practica.Domain.Model.HeroUIDetail
import com.example.android_superpoderes_practica.dataa.Remote.Repository
import com.example.android_superpoderes_practica.ui.HeroesDetail.HeroDetailState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
class HeroesDetailViewModelTest{

    private lateinit var heroesDetailviewModel: HeroesDetailViewModel
    private val repository: Repository = mockk()
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp(){
        Dispatchers.setMain(testDispatcher)
        heroesDetailviewModel = HeroesDetailViewModel(repository)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `WHEN getHeroDetail THEN success`()=  testDispatcher.runBlockingTest{
        val id = "123"
        val heroUIDetail = HeroUIDetail(
            id = 108234,
            name = "Nombre1",
            modified = "12012024",
            convertThumbnailToString = "photo",
            comics = Comics(available = "5", collectionURI = "", items = listOf(ComicsItem(name = "Comic 1")), returned = "5"),
            series = Comics(available = "3", collectionURI = "", items = listOf(ComicsItem(name = "Serie 1")), returned = "3")
        )
        coEvery { repository.getOneHeroListToRemote(id) } returns flowOf(heroUIDetail)

        // When
        heroesDetailviewModel.getOneHero(id)


        // Then
        heroesDetailviewModel.state.test {
            //assertEquals(HeroDetailState.Loading, awaitItem())
            assertEquals(HeroDetailState.Success(heroUIDetail), awaitItem())
        }
    }

    @Test
    fun `getHeroStatusFavourite WHEN repository returns false THEN isFavorite is false`() = runBlockingTest {
        // Given
        val heroId = 123L
        coEvery { repository.getHeroStatusFavourite(heroId) } returns false

        // When
        heroesDetailviewModel.getHeroStatusFavourite(heroId)

        // Then
        assertEquals(false, heroesDetailviewModel.isFavorite.value)
    }

    @Test
    fun `onFavoriteChanged WHEN called THEN updateFavoriteStatus is called with correct arguments`() = runBlockingTest {
        // Given
        val repository = mockk<Repository>()
        val viewModel = HeroesDetailViewModel(repository)
        val heroId = "123" // ID del héroe en formato String
        val isFavorite = true // Booleano que indica si el héroe es favorito o no

        // When
        viewModel.onFavoriteChanged(heroId, isFavorite)

        // Then
        coVerify { repository.updateFavoriteStatus(heroId.toLong(), isFavorite) }
    }
}
