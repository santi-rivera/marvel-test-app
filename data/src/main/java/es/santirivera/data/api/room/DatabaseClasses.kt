package es.santirivera.data.api.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MarvelCharacterDatabase(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "description") val description: String = "",
    @ColumnInfo(name = "thumbnail") val thumbnail: String = "",
    @ColumnInfo(name = "wikiUrl") val wikiUrl: String = ""
)

