package com.example.data.di

import com.example.data.datasource.UnsplashRemoteDataSource
import com.example.data.datasource.UnsplashRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal class DataSourceModule {
    @Provides
    @Singleton
    fun provideUnsplashRemoteDataSource(remoteDataSource: UnsplashRemoteDataSourceImpl): UnsplashRemoteDataSource =
        remoteDataSource
}