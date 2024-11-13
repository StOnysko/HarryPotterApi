package com.example.harrypotter.di

import android.content.Context
import androidx.room.Room
import com.example.harrypotter.data.room.AppDatabase
import com.example.harrypotter.data.room.CharacterSpellDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {
    @Provides
    @Singleton
    internal fun provideRoom(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "AppDatabase").build()

    @Provides
    @Singleton
    internal fun provideMapDao(room: AppDatabase): CharacterSpellDao = room.characterDao()
}