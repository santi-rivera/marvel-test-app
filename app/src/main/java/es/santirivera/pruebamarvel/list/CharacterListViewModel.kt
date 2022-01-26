package es.santirivera.pruebamarvel.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import es.santirivera.domain.model.MarvelCharacter
import es.santirivera.domain.usecase.Callback
import es.santirivera.domain.usecase.db.ClearDatabaseUseCase
import es.santirivera.domain.usecase.list.GetCharacterListUseCase
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharacterListUseCase,
    clearDatabaseUseCase: ClearDatabaseUseCase
) :
    ViewModel(), Callback<List<MarvelCharacter>> {

    private val _state = MutableLiveData<CharacterListState>()
    val state: LiveData<CharacterListState> get() = _state

    init {
        clearDatabaseUseCase.execute(null, object : Callback<Boolean> {
            override fun onSuccess(response: Boolean) {
                requestCharacters(1)
            }

            override fun onError(error: Exception) {
                reportError(error)
            }

        })
    }

    fun requestCharacters(offset: Int) {
        getCharactersUseCase.execute(null, this)
    }

    override fun onSuccess(response: List<MarvelCharacter>) {
        reportResult(response)
    }

    override fun onError(error: Exception) {
        reportError(error)
    }

    private fun reportResult(response: List<MarvelCharacter>) {
        val state = CharacterListState()
        state.list = response
        _state.value = state
    }

    private fun reportError(error: Exception) {
        val state = CharacterListState()
        state.error = error
        _state.value = state
    }

}