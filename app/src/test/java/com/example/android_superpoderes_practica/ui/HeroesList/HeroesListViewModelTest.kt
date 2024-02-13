package com.example.android_superpoderes_practica.ui.HeroesList

import app.cash.turbine.test
import com.example.android_superpoderes_practica.Domain.Model.HeroUI
import com.example.android_superpoderes_practica.dataa.Remote.Repository
import com.example.android_superpoderes_practica.ui.HeroesDetail.HeroesDetailViewModel
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
class HeroesListViewModelTest{

    private lateinit var heroesListViewModel: HeroesListViewModel
    private val repository: Repository = mockk()
    private val testDispatcher = TestCoroutineDispatcher()


    @Before
    fun setUp(){
        Dispatchers.setMain(testDispatcher)
        heroesListViewModel = HeroesListViewModel(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun cleanup() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `WHEN getHeroList THEN success`()=  testDispatcher.runBlockingTest{
        val offset = 0
        val heroUI = HeroUI(108234, "Nombre1", "photo", true)
        val herosUI = listOf(heroUI, heroUI, heroUI)

        coEvery { repository.getHeroList(offset) } returns flowOf(herosUI)

        //WHEN
        heroesListViewModel.getHerosList()

        heroesListViewModel.stateHero.test{
            assertEquals(HeroesListViewModel.HeroListState.Success(herosUI), awaitItem())

        }
    }

    @Test
    fun `WHEN change Heroes`() = testDispatcher.runBlockingTest {
        // Given
        val offset = 0
        coEvery { repository.insertMoreHeroes(0) } just runs

        // When
        val actual = heroesListViewModel.insertMoreHeroes()

        // Then
        assertEquals(Unit, actual)
    }

}