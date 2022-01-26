package es.santirivera.pruebamarvel.di

import android.content.Context
import android.content.res.AssetManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun providesAssetManager(@ApplicationContext context: Context): AssetManager {
        return context.assets
    }

    @Provides
    @Singleton
    fun providesMarvelDataSource(marvelApi: MarvelApi, assetManager:AssetManager): MarvelDataSource =
        MarvelDataSourceImpl(marvelApi, assetManager)

}