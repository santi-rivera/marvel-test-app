package es.santirivera.domain.repo

import es.santirivera.domain.model.MarvelCharacter
import kotlinx.coroutines.flow.Flow


interface CharacterRepository {

    suspend fun getCharactersList(limit: Int): List<MarvelCharacter>

    suspend fun requestMoreCharacters(limit: Int): Boolean

    fun getAllCharacters(): Flow<List<MarvelCharacter>>

    suspend fun clearDatabase(): Boolean

}