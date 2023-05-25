package com.example.domain.usecases

import com.example.domain.entity.UnsplashRepository
import com.example.domain.entity.model.PhotoResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val repository: UnsplashRepository) {
    suspend operator fun invoke(): Flow<com.example.domain.entity.model.Result<List<PhotoResult>>> {
        return repository.getPhotos()
    }
}