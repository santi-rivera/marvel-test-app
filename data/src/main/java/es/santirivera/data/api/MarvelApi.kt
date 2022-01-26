package es.santirivera.data.api

import es.santirivera.data.api.model.ResponseCharacter
import es.santirivera.data.api.model.ResponseCharacterListContainer
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): ResponseCharacterListContainer

}