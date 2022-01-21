package es.santirivera.domain.usecase.detail

import es.santirivera.domain.model.MarvelCharacter
import es.santirivera.domain.repo.CharacterRepository
import es.santirivera.domain.usecase.BaseUseCase

class GetCharacterDetailByIdUseCase(private val characterRepository: CharacterRepository) : BaseUseCase<String, MarvelCharacter>(){

    override suspend fun run(params: String?): MarvelCharacter {
        return characterRepository.getCharacterById(params!!)
    }
}