package es.santirivera.domain.usecase.clear

import es.santirivera.domain.repo.CharacterRepository
import es.santirivera.domain.usecase.Callback
import es.santirivera.domain.usecase.db.ClearDatabaseUseCase
import es.santirivera.domain.usecase.db.ClearDatabaseUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ClearDatabaseTest {

    @Mock
    private lateinit var characterRepository: CharacterRepository

    private lateinit var useCase: ClearDatabaseUseCase

    @ExperimentalCoroutinesApi
    private val dispatcher = StandardTestDispatcher()

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.openMocks(this)
        useCase = Mockito.spy(ClearDatabaseUseCaseImpl(characterRepository))
    }

    @Test
    fun testUseCase() {
        runBlocking {
            useCase.execute(null, object : Callback<Boolean> {
                override fun onSuccess(response: Boolean) {
                    assert(response)
                }

                override fun onError(error: Exception) {
                    assert(false)
                }
            })
        }
    }
}