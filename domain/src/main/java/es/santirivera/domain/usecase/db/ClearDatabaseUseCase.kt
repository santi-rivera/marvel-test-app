package es.santirivera.domain.usecase.db

import es.santirivera.domain.repo.CharacterRepository
import es.santirivera.domain.usecase.BaseUseCase

abstract class ClearDatabaseUseCase(private val characterRepository: CharacterRepository) : BaseUseCase<Void, Boolean>()