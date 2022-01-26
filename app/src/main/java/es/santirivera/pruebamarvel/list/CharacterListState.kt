package es.santirivera.pruebamarvel.list

import es.santirivera.domain.model.MarvelCharacter

class CharacterListState {
    var list: List<MarvelCharacter>? = null
    var error: Exception? = null
    val timeStamp = System.currentTimeMillis()

    override fun equals(other: Any?): Boolean {
        if (other is CharacterListState) return timeStamp == (other).timeStamp
        return false
    }
}