package es.santirivera.pruebamarvel.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import es.santirivera.domain.model.MarvelCharacter
import es.santirivera.domain.usecase.Callback
import es.santirivera.domain.usecase.list.GetCharacterListInput
import es.santirivera.domain.usecase.list.GetCharacterListUseCase
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(private val getCharactersUseCase: GetCharacterListUseCase) : ViewModel(), Callback<List<MarvelCharacter>> {

    private val _characterList = MutableLiveData<List<MarvelCharacter>>()
    val characterList : LiveData<List<MarvelCharacter>> get() = _characterList

    private var limit: Int = 10

    init { requestCharacters(1) }

    fun requestCharacters(offset: Int) {
        getCharactersUseCase.execute(GetCharacterListInput(limit, offset), this)
    }

    override fun onSuccess(response: List<MarvelCharacter>) {
        _characterList.value = response
    }

    override fun onError(error: Exception) {
        TODO("Not yet implemented")
    }

}