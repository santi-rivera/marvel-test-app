package es.santirivera.pruebamarvel.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.santirivera.domain.model.MarvelCharacter
import es.santirivera.domain.usecase.Callback
import es.santirivera.domain.usecase.db.ClearDatabaseUseCase
import es.santirivera.domain.usecase.list.flow.GetCharacterListFlowUseCase
import es.santirivera.domain.usecase.list.load.LoadMoreCharactersUseCase
import es.santirivera.pruebamarvel.list.state.CharacterListState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val loadMoreCharactersUseCase: LoadMoreCharactersUseCase,
    private val getCharacterListFlowUseCase: GetCharacterListFlowUseCase,
    clearDatabaseUseCase: ClearDatabaseUseCase
) :
    ViewModel(), Callback<Boolean> {

    private val _uiState = MutableStateFlow(CharacterListState())
    val uiState: StateFlow<CharacterListState> = _uiState


    init {
        clearDatabaseUseCase.execute(null, object : Callback<Boolean> {
            override fun onSuccess(response: Boolean) {
                requestCharacters()
            }

            override fun onError(error: Exception) {
                reportError(error)
            }
        })
        viewModelScope.launch {
            getCharacterListFlowUseCase.resultFlow.collect {
                reportResult(it)
            }
        }

    }

    fun requestCharacters() {
        loadMoreCharactersUseCase.execute(null, this)
    }

    override fun onSuccess(response: Boolean) {
        //reportResult(response)
    }

    override fun onError(error: Exception) {
        reportError(error)
    }

    private fun reportResult(response: List<MarvelCharacter>) {
        val state = CharacterListState()
        state.list = response
        _uiState.value = state
    }

    private fun reportError(error: Exception) {
        val state = CharacterListState()
        state.error = error
        _uiState.value = state
    }

}