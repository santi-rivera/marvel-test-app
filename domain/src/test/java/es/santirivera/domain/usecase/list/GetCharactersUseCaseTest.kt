package es.santirivera.domain.usecase.list

import es.santirivera.domain.model.MarvelCharacter
import es.santirivera.domain.repo.CharacterRepository
import es.santirivera.domain.usecase.Callback
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
import org.mockito.Mockito.spy
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCharactersUseCaseTest {

    @Mock
    private lateinit var characterRepository: CharacterRepository

    private lateinit var useCase: GetCharacterListUseCase

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
        useCase = spy(GetCharacterListUseCaseImpl(characterRepository))
    }

    @Test
    fun testUseCase() {
        runBlocking {
            useCase.execute(null, object : Callback<List<MarvelCharacter>> {
                override fun onSuccess(response: List<MarvelCharacter>) {
                    assert(response.isNotEmpty())
                }

                override fun onError(error: Exception) {
                    assert(false)
                }
            })
        }
    }

}