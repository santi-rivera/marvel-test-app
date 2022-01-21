package es.santirivera.domain.repo

import es.santirivera.domain.model.MarvelCharacter


interface CharacterRepository {

    suspend fun getCharactersList(limit : Int, offset : Int) : List<MarvelCharacter>

    suspend fun getCharacterById(id : String) : MarvelCharacter

}