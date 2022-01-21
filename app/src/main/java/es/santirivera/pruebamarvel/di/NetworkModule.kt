package es.santirivera.pruebamarvel.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.santirivera.data.api.MarvelApi
import es.santirivera.data.api.hash.Hash
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val baseUrl = "https://gateway.marvel.com/"
    private val publicKey = "70727995a318873b4d47ac68038ba995"
    private val privateKey = "a652c19b3552cdf872b9660e6e2d39a7ea0fe072"

    private fun addHashAndPublicKey(chain: Interceptor.Chain): okhttp3.Response {
        val timeStamp = System.currentTimeMillis()/1000
        var request = chain.request()
        val builder = request.url.newBuilder()
        builder.addQueryParameter("apikey", publicKey).addQueryParameter("hash", Hash.generate(timeStamp, privateKey, publicKey)).addQueryParameter("ts", timeStamp.toString())
        request = request.newBuilder().url(builder.build()).build()
        return chain.proceed(request)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient() : OkHttpClient=OkHttpClient.Builder().addInterceptor { interceptorChain -> addHashAndPublicKey(interceptorChain) }.build()

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient ): Retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()

    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit) : MarvelApi = retrofit.create(MarvelApi::class.java)





}