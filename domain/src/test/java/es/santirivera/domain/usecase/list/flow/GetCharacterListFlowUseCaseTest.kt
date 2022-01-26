package es.santirivera.domain.usecase.list.flow

import es.santirivera.domain.repo.CharacterRepository
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

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetCharacterListFlowUseCaseTest {

    @Mock
    private lateinit var characterRepository: CharacterRepository

    private lateinit var useCase: GetCharacterListFlowUseCase

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
        useCase = spy(GetCharacterListFlowUseCaseImpl(characterRepository))
    }

    @Test
    fun testUseCase() {
        runBlocking {
            useCase.resultFlow.collect {
                assert(it.isNotEmpty())
            }
        }
    }

}