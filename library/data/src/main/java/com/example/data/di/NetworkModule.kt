package com.example.data.di

import android.util.Log
import com.example.data.network.UnsplashApi
import com.example.data.network.UnsplashNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal class NetworkModule {

    @Singleton
    @Provides
    fun provideBaseUrl(): String =
        "https://api.unsplash.com//search/photos/?client_id=zvfR8DbCZDEtLYZH0ptqv34zYDacU_Y4ezw9bhFSLBY&query=landscape&per_page=25&orientation=portrait&page=1"


    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        val client = HttpClient(Android) {
            engine {
                connectTimeout = 60_000
                socketTimeout = 60_000
            }
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("loggerTag", message)
                    }
                }
                level = LogLevel.ALL
            }
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                    }
                )
            }
        }
        return client
    }

    @Singleton
    @Provides
    fun provideUnsplashApi(unsplashNetwork: UnsplashNetwork): UnsplashApi =
        unsplashNetwork
}