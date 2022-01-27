package es.santirivera.domain.usecase.list.load

import es.santirivera.domain.repo.CharacterRepository
import es.santirivera.domain.usecase.BaseUseCase

abstract class LoadMoreCharactersUseCase(private val characterRepository: CharacterRepository) : BaseUseCase<Void, Boolean>()