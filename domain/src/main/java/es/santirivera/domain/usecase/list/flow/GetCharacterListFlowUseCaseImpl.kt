package es.santirivera.domain.usecase.list.flow

import es.santirivera.domain.model.MarvelCharacter
import es.santirivera.domain.repo.CharacterRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class GetCharacterListFlowUseCaseImpl (private val characterRepository: CharacterRepository) :
    GetCharacterListFlowUseCase(characterRepository) {
    override fun performAction(): Flow<List<MarvelCharacter>> {
        return characterRepository.getAllCharacters()
    }
}