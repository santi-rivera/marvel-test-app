package es.santirivera.domain.usecase

import es.santirivera.domain.model.MarvelCharacter

interface UseCase<in Request, Response> {

    fun execute(params: Request? = null, callback: Callback<Response>?)

}