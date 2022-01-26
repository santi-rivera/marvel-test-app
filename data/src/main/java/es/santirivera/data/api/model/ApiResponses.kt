package es.santirivera.data.api.model

data class ResponseCharacterListContainer(
    val data: ResponseCharacterList
)

data class ResponseCharacterList(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: ArrayList<ResponseCharacter>
)

data class ResponseCharacter(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val thumbnail: Thumbnail? = null,
    val urls: ArrayList<CharacterUrl>
)

data class CharacterUrl(
    val type: String = "",
    val url: String = ""
)

data class Thumbnail(
    val extension: String = "",
    val path: String = ""
)