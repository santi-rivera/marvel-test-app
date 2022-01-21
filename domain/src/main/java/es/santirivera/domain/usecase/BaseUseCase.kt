package es.santirivera.domain.usecase

import kotlinx.coroutines.*

abstract class BaseUseCase<in Request, Response> : UseCase<Request, Response> {

    private var job = Job()
    private var uiScope = CoroutineScope(Dispatchers.Main + job)

    @Throws(Exception::class)
    abstract suspend fun run(params: Request? = null): Response

    open fun invoke(params: Request? = null, callback: Callback<Response>){
        uiScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    run(params)
                }
                callback.onSuccess(result)
            } catch (e: Exception) {
                callback.onError(e)
            }
        }
    }

    open fun dispose() {
        job.cancel()
    }

    override fun execute(params: Request?, callback: Callback<Response>) {
        invoke(params, callback)
    }
}