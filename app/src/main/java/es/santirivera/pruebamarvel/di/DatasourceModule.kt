package es.santirivera.pruebamarvel.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.santirivera.data.api.MarvelApi
import es.santirivera.data.api.MarvelDataSource
import es.santirivera.data.api.MarvelDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatasourceModule {

    @Provides
    @Singleton
    fun providesMarvelDataSource(marvelApi: MarvelApi): MarvelDataSource = MarvelDataSourceImpl(marvelApi)

}