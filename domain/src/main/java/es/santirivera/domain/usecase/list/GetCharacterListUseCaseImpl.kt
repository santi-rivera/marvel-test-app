package es.santirivera.domain.usecase.list

import es.santirivera.domain.model.MarvelCharacter
import es.santirivera.domain.repo.CharacterRepository
import es.santirivera.domain.usecase.BaseUseCase

class GetCharacterListUseCaseImpl (private val characterRepository: CharacterRepository) :
    GetCharacterListUseCase(characterRepository) {

    override suspend fun run(params: GetCharacterListInput?): List<MarvelCharacter> {
        return characterRepository.getCharactersList(params!!.limit, params.offset)
    }
}