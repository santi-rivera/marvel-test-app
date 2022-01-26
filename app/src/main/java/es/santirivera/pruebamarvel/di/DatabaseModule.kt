package es.santirivera.pruebamarvel.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.santirivera.data.api.room.Database
import es.santirivera.data.api.room.MarvelCharacterDao
import es.santirivera.pruebamarvel.BuildConfig
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesLocalDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(context, Database::class.java, BuildConfig.DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    fun providesLocalMarvelCharacterDao(roomDatabase: Database): MarvelCharacterDao {
        return roomDatabase.marvelCharacterDao()
    }
}