package com.example.android_superpoderes_practica.di

import android.content.Context
import androidx.room.Room
import com.example.android_superpoderes_practica.data.local.HeroDAO
import com.example.android_superpoderes_practica.data.local.HeroDatabase
import com.example.android_superpoderes_practica.data.local.LocalDataSource
import com.example.android_superpoderes_practica.data.local.LocalDataSourceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Provides
    fun providesHeroDatabase(@ApplicationContext context: Context): HeroDatabase {
        return Room.databaseBuilder(
            context,
            HeroDatabase::class.java, "hero-database"
        ).build()
    }

    @Provides
    fun providesHeroDao(db: HeroDatabase): HeroDAO {
        return db.heroDao()
    }

    @Provides
    fun providesLocalDataSourceInterface(localDataSource: LocalDataSource): LocalDataSourceInterface {
        return localDataSource
    }
}