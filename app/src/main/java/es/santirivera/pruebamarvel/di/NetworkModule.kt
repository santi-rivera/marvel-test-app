package es.santirivera.pruebamarvel.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.santirivera.data.api.MarvelApi
import es.santirivera.data.api.hash.Hash
import es.santirivera.pruebamarvel.Keys
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val baseUrl = "https://gateway.marvel.com/"

    private fun addHashAndPublicKey(chain: Interceptor.Chain): okhttp3.Response {
        val timeStamp = System.currentTimeMillis() / 1000
        var request = chain.request()
        val builder = request.url.newBuilder()
        builder.addQueryParameter("apikey", Keys.PUBLIC_KEY)
            .addQueryParameter("hash", Hash.generate(timeStamp, Keys.PUBLIC_KEY, Keys.PRIVATE_KEY))
            .addQueryParameter("ts", timeStamp.toString())
        request = request.newBuilder().url(builder.build()).build()
        return chain.proceed(request)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { interceptorChain -> addHashAndPublicKey(interceptorChain) }.build()

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient).build()

    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): MarvelApi = retrofit.create(MarvelApi::class.java)


}