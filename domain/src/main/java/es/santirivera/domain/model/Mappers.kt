package es.santirivera.domain.model

import es.santirivera.data.api.model.ResponseCharacter
import es.santirivera.data.api.room.MarvelCharacterDatabase


fun ResponseCharacter.toDatabaseCharacter(): MarvelCharacterDatabase {
    return MarvelCharacterDatabase(
        id,
        name,
        description,
        "${thumbnail?.path}.${thumbnail?.extension}",
        getWikiUrl()
    )
}

fun ResponseCharacter.toMarvelCharacter(): MarvelCharacter {
    return MarvelCharacter(
        id,
        name,
        description,
        "${thumbnail?.path}.${thumbnail?.extension}",
        getWikiUrl()
    )
}

fun MarvelCharacterDatabase.toMarvelCharacter(): MarvelCharacter {
    return MarvelCharacter(id, name, description, thumbnail, wikiUrl)
}

fun List<MarvelCharacterDatabase>.toMarvelCharacterList():List<MarvelCharacter>{
    val list = ArrayList<MarvelCharacter>()
    for (character in this){
        list.add(character.toMarvelCharacter())
    }
    return list
}

fun ResponseCharacter.getWikiUrl(): String {
    var wikiUrl = ""
    for (url in urls) {
        if (url.type == "wiki") {
            wikiUrl = url.url
        }
    }
    return wikiUrl
}