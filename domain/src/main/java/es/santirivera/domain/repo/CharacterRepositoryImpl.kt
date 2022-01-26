package es.santirivera.domain.repo


import es.santirivera.data.api.MarvelDataSource
import es.santirivera.data.api.room.MarvelCharacterDao
import es.santirivera.domain.exception.EmptyListException
import es.santirivera.domain.model.MarvelCharacter
import es.santirivera.domain.model.toDatabaseCharacter
import es.santirivera.domain.model.toMarvelCharacter


class CharacterRepositoryImpl(
    private val dataSource: MarvelDataSource,
    private val characterDao: MarvelCharacterDao
) : CharacterRepository {

    override suspend fun getCharactersList(limit: Int): List<MarvelCharacter> {
        return try {
            val totalList = ArrayList<MarvelCharacter>()
            val localList = characterDao.getAll().map {
                it.toMarvelCharacter()
            }
            val list = dataSource.getCharacterList(limit, localList.size + 1)
            if (list.data.results.isEmpty()) {
                throw EmptyListException()
            }
            val sanitizedList = list.data.results.map {
                it.toMarvelCharacter()
            }
            val localSanitizedList = list.data.results.map {
                it.toDatabaseCharacter()
            }
            characterDao.insertAll(localSanitizedList)
            totalList.addAll(localList)
            totalList.addAll(sanitizedList)
            return totalList
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

    override suspend fun clearDatabase(): Boolean {
        return try {
            characterDao.clearTable()
            true
        } catch (exception: Exception) {
            throw exception
        }
    }

}