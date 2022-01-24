package es.santirivera.domain.repo


import es.santirivera.data.api.MarvelDataSource
import es.santirivera.data.api.model.ResponseCharacter
import es.santirivera.domain.exception.EmptyListException
import es.santirivera.domain.model.MarvelCharacter
import java.lang.RuntimeException

class CharacterRepositoryImpl(private val dataSource: MarvelDataSource) : CharacterRepository {

    override suspend fun getCharactersList(limit: Int, offset: Int): List<MarvelCharacter> {
        return try {
            val list = dataSource.getCharacterList(limit, offset)
            if (list.data.results.isEmpty()){
                throw EmptyListException()
            } else {
                val sanitizedList = ArrayList<MarvelCharacter>()
                for (character in list.data.results){
                    sanitizedList.add(character.toMarvelCharacter())
                }
                return sanitizedList
            }
        } catch (exception: Exception) {
            throw exception
        }
    }

    override suspend fun getCharacterById(id: String): MarvelCharacter {
        return try {
            val response = dataSource.getCharacterById(id)
            response.toMarvelCharacter()
        } catch (exception: Exception) {
            throw exception
        }
    }

    private fun ResponseCharacter.toMarvelCharacter(): MarvelCharacter {
        return MarvelCharacter(id,name, description, "${thumbnail?.path}.${thumbnail?.extension}")
    }
}