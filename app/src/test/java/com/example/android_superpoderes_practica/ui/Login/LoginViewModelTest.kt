
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.time.ExperimentalTime
import app.cash.turbine.test
import com.example.android_superpoderes_practica.dataa.Remote.Repository
import com.example.android_superpoderes_practica.ui.Login.LoginState
import com.example.android_superpoderes_practica.ui.Login.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    private lateinit var loginviewModel: LoginViewModel
    private val repository: Repository = mockk()
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        loginviewModel = LoginViewModel(repository)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun `whe launchLogin THEN success and return token String`() =  testDispatcher.runBlockingTest {
        // Given
        val userName = "user"
        val password = "password"
        val token = "12345"
        coEvery { repository.launchLogin(userName, password) } returns token

        // When
        loginviewModel.launchLogin(userName, password)

        // Then
        loginviewModel.state.test {
            assertEquals(LoginState.Success(token), awaitItem())

        }
    }
}
