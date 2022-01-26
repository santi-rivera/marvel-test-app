package es.santirivera.data.api.room

import androidx.room.*

@Dao
interface MarvelCharacterDao {

    @Query("SELECT * FROM MarvelCharacterDatabase")
    fun getAll(): List<MarvelCharacterDatabase>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<MarvelCharacterDatabase>)

    @Query("DELETE FROM MarvelCharacterDatabase")
    suspend fun clearTable()

}