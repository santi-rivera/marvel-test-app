package es.santirivera.domain.usecase.detail

import es.santirivera.domain.model.MarvelCharacter
import es.santirivera.domain.repo.CharacterRepository
import es.santirivera.domain.usecase.BaseUseCase

abstract class GetCharacterDetailByIdUseCase(private val characterRepository: CharacterRepository) : BaseUseCase<String, MarvelCharacter>(){

}