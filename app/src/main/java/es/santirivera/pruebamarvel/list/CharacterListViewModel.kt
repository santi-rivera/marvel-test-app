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
class CharacterListViewModel @Inject constructor(private val getCharactersUseCase: GetCharacterListUseCase, clearDatabaseUseCase: ClearDatabaseUseCase) :
    ViewModel(), Callback<List<MarvelCharacter>>{

    private val _characterList = MutableLiveData<List<MarvelCharacter>>()
    val characterList: LiveData<List<MarvelCharacter>> get() = _characterList

    private val _error = MutableLiveData<Exception>()
    val exception: LiveData<Exception> get() = _error

    init {
        clearDatabaseUseCase.execute(null, object:Callback<Boolean>{
            override fun onSuccess(response: Boolean) {
                requestCharacters(1)
            }

            override fun onError(error: Exception) {
                _error.value = error
            }
        })
    }

    fun requestCharacters(offset: Int) {
        getCharactersUseCase.execute(null, this)
    }

    override fun onSuccess(response: List<MarvelCharacter>) {
        _characterList.value = response
    }

    override fun onError(error: Exception) {
        _error.value = error
    }

}