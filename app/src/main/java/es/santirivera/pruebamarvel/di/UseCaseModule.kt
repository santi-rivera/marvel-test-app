package es.santirivera.pruebamarvel.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import es.santirivera.domain.repo.CharacterRepository
import es.santirivera.domain.usecase.db.ClearDatabaseUseCase
import es.santirivera.domain.usecase.db.ClearDatabaseUseCaseImpl
import es.santirivera.domain.usecase.list.flow.GetCharacterListFlowUseCase
import es.santirivera.domain.usecase.list.flow.GetCharacterListFlowUseCaseImpl
import es.santirivera.domain.usecase.list.load.LoadMoreCharactersUseCase
import es.santirivera.domain.usecase.list.load.LoadMoreCharactersUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    fun providesClearDatabaseUseCase(characterRepository: CharacterRepository): ClearDatabaseUseCase =
        ClearDatabaseUseCaseImpl(characterRepository)

    @ExperimentalCoroutinesApi
    @Provides
    fun providesGetCharacterListFlowUseCase(characterRepository: CharacterRepository): GetCharacterListFlowUseCase =
        GetCharacterListFlowUseCaseImpl(characterRepository)

    @ExperimentalCoroutinesApi
    @Provides
    fun providesLoadMoreCharactersUseCase(characterRepository: CharacterRepository): LoadMoreCharactersUseCase =
        LoadMoreCharactersUseCaseImpl(characterRepository)
}