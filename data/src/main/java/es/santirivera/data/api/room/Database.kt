package es.santirivera.data.api.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MarvelCharacterDatabase::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun marvelCharacterDao(): MarvelCharacterDao

}