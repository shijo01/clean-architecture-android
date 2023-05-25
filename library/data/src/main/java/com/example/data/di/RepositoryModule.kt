package com.example.data.di


import com.example.data.repository.UnsplashRepositoryImpl
import com.example.domain.entity.UnsplashRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal class RepositoryModule {

    @Provides
    @Singleton
    fun provideUnsplashRepository(repository: UnsplashRepositoryImpl): UnsplashRepository =
        repository
}