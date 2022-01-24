package es.santirivera.domain.usecase.detail

import es.santirivera.domain.model.MarvelCharacter
import es.santirivera.domain.repo.CharacterRepository
import es.santirivera.domain.usecase.BaseUseCase

class GetCharacterDetailByIdUseCaseImpl(private val characterRepository: CharacterRepository) : GetCharacterDetailByIdUseCase(characterRepository) {

    override suspend fun run(params: String?): MarvelCharacter {
        return characterRepository.getCharacterById(params!!)
    }
}