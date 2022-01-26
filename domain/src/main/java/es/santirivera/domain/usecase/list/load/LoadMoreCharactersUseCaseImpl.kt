package es.santirivera.domain.usecase.list.load

import es.santirivera.domain.repo.CharacterRepository

class LoadMoreCharactersUseCaseImpl(private val characterRepository: CharacterRepository) :
    LoadMoreCharactersUseCase(characterRepository) {

    override suspend fun run(params: Void?): Boolean {
        return characterRepository.requestMoreCharacters(30)
    }
}