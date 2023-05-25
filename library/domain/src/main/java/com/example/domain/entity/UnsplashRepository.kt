package com.example.domain.entity

import com.example.domain.entity.model.PhotoResult
import kotlinx.coroutines.flow.Flow

interface UnsplashRepository {
    suspend fun getPhotos(): Flow<com.example.domain.entity.model.Result<List<PhotoResult>>>
}