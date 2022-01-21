package es.santirivera.domain.usecase

interface Callback<Response> {

    fun onSuccess(response: Response)
    fun onError(error: java.lang.Exception)

}