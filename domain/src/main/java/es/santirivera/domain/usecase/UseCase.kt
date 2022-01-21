package es.santirivera.domain.usecase

interface UseCase<in Request, Response> {

    fun execute(params: Request? = null, callback: Callback<Response>)

}