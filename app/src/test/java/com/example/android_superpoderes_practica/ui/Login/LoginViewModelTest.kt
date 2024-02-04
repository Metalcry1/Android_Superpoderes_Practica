package com.example.android_superpoderes_practica.ui.Login

import com.example.android_superpoderes_practica.Data.Remote.Repository
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

// SUT
private lateinit var loginViewModel: LoginViewModel

// Depencias
private val repository: Repository = mockk()
private val mainThreadSurrogate = newSingleThreadContext("UI thread")

@BeforeEach
fun setUp() {
    loginViewModel = LoginViewModel(repository, UnconfinedTestDispatcher())
    Dispatchers.setMain(mainThreadSurrogate)
}

@Test
fun `WHEN launchLogin THEN return state loading and success String`() = runTest {
    val userName = "userName"
    val password = "password"
    val token = "token"

    // GIVEN
    coEvery { repository.launchLogin(userName, password) } returns token

    // WHEN
    val actual = loginViewModel.launchLogin(userName, password)

    val actualLiveDataValue1 = loginViewModel.state
    Truth.assertThat(actualLiveDataValue1).isEqualTo(LoginState(token))

    advanceUntilIdle()

    val actualLiveDataValue2 = loginViewModel.
    Truth.assertThat(actualLiveDataValue2).isEqualTo(LoginState(token))
}

@AfterEach
fun tearDown() {
    Dispatchers.resetMain()
}


