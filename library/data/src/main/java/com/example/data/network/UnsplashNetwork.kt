package com.example.data.network

import com.example.data.network.model.UnsplashResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

internal class UnsplashNetwork @Inject constructor(
    private val httpClient: HttpClient,
    private val baseUrl: String
) : UnsplashApi {
    override suspend fun getPhotos(): UnsplashResponseDto {
        return httpClient.get<UnsplashResponseDto>(baseUrl)
    }
}