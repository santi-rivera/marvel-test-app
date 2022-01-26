package es.santirivera.domain.usecase.list

import es.santirivera.domain.model.MarvelCharacter
import es.santirivera.domain.repo.CharacterRepository

open class GetCharacterListUseCaseImpl(private val characterRepository: CharacterRepository) :
    GetCharacterListUseCase(characterRepository) {

    override suspend fun run(params: Void?): List<MarvelCharacter> {
        return characterRepository.getCharactersList(30)
    }
}