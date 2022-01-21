package es.santirivera.data.api

import es.santirivera.data.api.model.ResponseCharacter
import es.santirivera.data.api.model.ResponseCharacterListContainer

interface MarvelDataSource {

    @Throws(Exception::class)
    suspend fun getCharacterList(limit: Int, offset: Int) : ResponseCharacterListContainer

    @Throws(Exception::class)
    suspend fun getCharacterById(id : String): ResponseCharacter

}