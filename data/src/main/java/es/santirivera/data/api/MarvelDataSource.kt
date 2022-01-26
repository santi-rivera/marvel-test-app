package es.santirivera.data.api

import es.santirivera.data.api.model.ResponseCharacterListContainer

interface MarvelDataSource {

    @Throws(Exception::class)
    suspend fun getCharacterList(limit: Int, offset: Int): ResponseCharacterListContainer
}