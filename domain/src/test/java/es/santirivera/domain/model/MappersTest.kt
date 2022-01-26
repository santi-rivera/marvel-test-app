package es.santirivera.domain.model

import es.santirivera.data.api.model.CharacterUrl
import es.santirivera.data.api.model.ResponseCharacter
import es.santirivera.data.api.model.Thumbnail
import es.santirivera.data.api.room.MarvelCharacterDatabase
import org.junit.Assert.assertEquals
import org.junit.Test

class MappersTest {

    @Test
    fun testDatabaseToCharacter() {
        val marvelDatabaseCharacter = MarvelCharacterDatabase("a", "b", "c", "d", "e")
        val marvelCharacter = MarvelCharacter("a", "b", "c", "d", "e")

        val marvelCharacterTransformed = marvelDatabaseCharacter.toMarvelCharacter()

        assertEquals(marvelCharacter.id, marvelCharacterTransformed.id)
        assertEquals(marvelCharacter.name, marvelCharacterTransformed.name)
        assertEquals(marvelCharacter.description, marvelCharacterTransformed.description)
        assertEquals(marvelCharacter.image, marvelCharacterTransformed.image)
        assertEquals(marvelCharacter.wikiUrl, marvelCharacterTransformed.wikiUrl)
    }

    @Test
    fun testResponseToCharacter() {
        val marvelCharacter = MarvelCharacter("a", "b", "c", "image.jpg", "www.google.com")
        val thumbnail = Thumbnail(path = "image", extension = "jpg")
        val wikiCharacterUrl = CharacterUrl(type = "wiki", url = "www.google.com")
        val characterUrls = ArrayList<CharacterUrl>()
        characterUrls.add(wikiCharacterUrl)

        val responseCharacter = ResponseCharacter(
            id = "a",
            name = "b",
            description = "c",
            thumbnail = thumbnail,
            urls = characterUrls
        )
        val responseAsMarvelCharacter = responseCharacter.toMarvelCharacter()

        assertEquals(marvelCharacter.id, responseAsMarvelCharacter.id)
        assertEquals(marvelCharacter.name, responseAsMarvelCharacter.name)
        assertEquals(marvelCharacter.description, responseAsMarvelCharacter.description)
        assertEquals(marvelCharacter.image, responseAsMarvelCharacter.image)
        assertEquals(marvelCharacter.wikiUrl, responseAsMarvelCharacter.wikiUrl)
    }

    @Test
    fun testResponseToDatabase() {
        val marvelDatabaseCharacter =
            MarvelCharacterDatabase("a", "b", "c", "image.jpg", "www.google.com")
        val thumbnail = Thumbnail(path = "image", extension = "jpg")
        val wikiCharacterUrl = CharacterUrl(type = "wiki", url = "www.google.com")
        val characterUrls = ArrayList<CharacterUrl>()
        characterUrls.add(wikiCharacterUrl)

        val responseCharacter = ResponseCharacter(
            id = "a",
            name = "b",
            description = "c",
            thumbnail = thumbnail,
            urls = characterUrls
        )
        val responseAsDatabaseCharacter = responseCharacter.toDatabaseCharacter()

        assertEquals(marvelDatabaseCharacter.id, responseAsDatabaseCharacter.id)
        assertEquals(marvelDatabaseCharacter.name, responseAsDatabaseCharacter.name)
        assertEquals(marvelDatabaseCharacter.description, responseAsDatabaseCharacter.description)
        assertEquals(marvelDatabaseCharacter.thumbnail, responseAsDatabaseCharacter.thumbnail)
        assertEquals(marvelDatabaseCharacter.wikiUrl, responseAsDatabaseCharacter.wikiUrl)
    }

}