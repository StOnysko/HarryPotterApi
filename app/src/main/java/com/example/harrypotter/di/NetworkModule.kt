package com.example.harrypotter.di

import com.example.harrypotter.data.network.ApiService
import com.example.harrypotter.data.repository.ServiceRepository
import com.example.harrypotter.data.room.CharacterSpellDao
import com.example.harrypotter.util.Utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideApiServiceRepository(apiService: ApiService, dao: CharacterSpellDao): ServiceRepository {
        return ServiceRepository(dao = dao, api = apiService)
    }
}
