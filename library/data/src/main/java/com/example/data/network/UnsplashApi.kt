package com.example.data.network

import com.example.data.network.model.UnsplashResponseDto

internal interface UnsplashApi {
    suspend fun getPhotos(): UnsplashResponseDto
}