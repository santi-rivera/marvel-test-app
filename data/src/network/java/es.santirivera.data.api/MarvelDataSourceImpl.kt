package es.santirivera.data.api

import es.santirivera.data.api.model.ResponseCharacter
import es.santirivera.data.api.model.ResponseCharacterListContainer
import java.net.UnknownHostException

class MarvelDataSourceImpl(
    private val marvelApi: MarvelApi,
    private val assetsManager: AssetManager
) : MarvelDataSource {

    @Throws(UnknownHostException::class)
    override suspend fun getCharacterList(limit: Int, offset: Int): ResponseCharacterListContainer {
        return marvelApi.getAllCharacters(limit, offset)
    }

}