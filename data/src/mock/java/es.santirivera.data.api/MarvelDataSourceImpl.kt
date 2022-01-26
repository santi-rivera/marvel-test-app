package es.santirivera.data.api

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import es.santirivera.data.api.model.ResponseCharacterListContainer
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class MarvelDataSourceImpl(
    private val marvelApi: MarvelApi,
    private val assetsManager: AssetManager
) : MarvelDataSource {

    override suspend fun getCharacterList(limit: Int, offset: Int): ResponseCharacterListContainer {
        val inSt: InputStream = assetsManager.open("character_list.json")
        val reader = JsonReader(BufferedReader(InputStreamReader(inSt)))
        return Gson().fromJson(reader, ResponseCharacterListContainer::class.java)
    }

}