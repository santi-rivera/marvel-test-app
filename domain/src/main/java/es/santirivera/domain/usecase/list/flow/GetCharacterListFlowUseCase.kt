package es.santirivera.domain.usecase.list.flow

import es.santirivera.domain.model.MarvelCharacter
import es.santirivera.domain.repo.CharacterRepository
import es.santirivera.domain.usecase.FlowUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
abstract class GetCharacterListFlowUseCase(private val characterRepository: CharacterRepository) : FlowUseCase<List<MarvelCharacter>>()