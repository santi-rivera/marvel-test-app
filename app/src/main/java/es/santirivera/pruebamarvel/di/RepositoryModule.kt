package es.santirivera.pruebamarvel.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.santirivera.data.api.MarvelDataSource
import es.santirivera.domain.repo.CharacterRepository
import es.santirivera.domain.repo.CharacterRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesCharacterRepository(dataSource: MarvelDataSource): CharacterRepository =
        CharacterRepositoryImpl(dataSource)

}