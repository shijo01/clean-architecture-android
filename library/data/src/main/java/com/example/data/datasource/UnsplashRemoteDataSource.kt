package com.example.data.datasource

import com.example.domain.entity.model.PhotoResult

internal interface UnsplashRemoteDataSource {
    suspend fun getPhotos(): List<PhotoResult>
}