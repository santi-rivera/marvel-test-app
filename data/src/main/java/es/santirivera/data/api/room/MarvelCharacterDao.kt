package es.santirivera.data.api.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MarvelCharacterDao {

    @Query("SELECT * FROM MarvelCharacterDatabase")
    fun getAll(): List<MarvelCharacterDatabase>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<MarvelCharacterDatabase>)

    @Query("DELETE FROM MarvelCharacterDatabase")
    suspend fun clearTable()

}