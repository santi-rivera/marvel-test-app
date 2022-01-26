package es.santirivera.domain.usecase.db

import es.santirivera.domain.repo.CharacterRepository

open class ClearDatabaseUseCaseImpl(private val characterRepository: CharacterRepository) :
    ClearDatabaseUseCase(characterRepository) {
    override suspend fun run(params: Void?): Boolean {
        return characterRepository.clearDatabase()
    }
}