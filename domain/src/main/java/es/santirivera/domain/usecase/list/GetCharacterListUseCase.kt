package es.santirivera.domain.usecase.list

import es.santirivera.domain.model.MarvelCharacter
import es.santirivera.domain.repo.CharacterRepository
import es.santirivera.domain.usecase.BaseUseCase

class GetCharacterListUseCase(private val characterRepository: CharacterRepository) :
    BaseUseCase<GetCharacterListInput, List<MarvelCharacter>>() {

    override suspend fun run(params: GetCharacterListInput?): List<MarvelCharacter> {
        return characterRepository.getCharactersList(params!!.limit, params.offset)
    }
}