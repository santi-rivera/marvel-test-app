package es.santirivera.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MarvelCharacter(
    val id: String,
    val name: String,
    val description: String,
    val image: String,
    val wikiUrl: String = ""
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (other is MarvelCharacter) {
            return other.id == id
        }
        return false
    }
}