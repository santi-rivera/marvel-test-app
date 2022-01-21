package es.santirivera.pruebamarvel.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import es.santirivera.domain.repo.CharacterRepository
import es.santirivera.domain.usecase.detail.GetCharacterDetailByIdUseCase
import es.santirivera.domain.usecase.list.GetCharacterListUseCase

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun providesGetCharacterByIdUseCase(characterRepository: CharacterRepository): GetCharacterDetailByIdUseCase = GetCharacterDetailByIdUseCase(characterRepository)

    @Provides
    fun providesGetCharacterListUseCase(characterRepository : CharacterRepository) : GetCharacterListUseCase = GetCharacterListUseCase(characterRepository)

}