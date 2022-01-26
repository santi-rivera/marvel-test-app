package es.santirivera.pruebamarvel.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import es.santirivera.domain.repo.CharacterRepository
import es.santirivera.domain.usecase.db.ClearDatabaseUseCase
import es.santirivera.domain.usecase.db.ClearDatabaseUseCaseImpl
import es.santirivera.domain.usecase.detail.GetCharacterDetailByIdUseCase
import es.santirivera.domain.usecase.detail.GetCharacterDetailByIdUseCaseImpl
import es.santirivera.domain.usecase.list.GetCharacterListUseCase
import es.santirivera.domain.usecase.list.GetCharacterListUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun providesGetCharacterByIdUseCase(characterRepository: CharacterRepository): GetCharacterDetailByIdUseCase =
        GetCharacterDetailByIdUseCaseImpl(characterRepository)

    @Provides
    fun providesGetCharacterListUseCase(characterRepository: CharacterRepository): GetCharacterListUseCase =
        GetCharacterListUseCaseImpl(characterRepository)

    @Provides
    fun providesClearDatabaseUseCase(characterRepository: CharacterRepository): ClearDatabaseUseCase =
        ClearDatabaseUseCaseImpl(characterRepository)
}