package es.santirivera.domain.usecase.list

import es.santirivera.domain.model.MarvelCharacter
import es.santirivera.domain.repo.CharacterRepository
import es.santirivera.domain.usecase.BaseUseCase

abstract class GetCharacterListUseCase(private val characterRepository: CharacterRepository) : BaseUseCase<Void, List<MarvelCharacter>>() {}