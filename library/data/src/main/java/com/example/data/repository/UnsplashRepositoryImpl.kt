package com.example.data.repository

import com.example.data.datasource.UnsplashRemoteDataSource
import com.example.domain.entity.UnsplashRepository
import com.example.domain.entity.model.PhotoResult
import com.example.domain.entity.model.Result
import com.example.domain.entity.model.getResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class UnsplashRepositoryImpl @Inject constructor(
    private val unsplashRemoteDataSource: UnsplashRemoteDataSource
) : UnsplashRepository {
    override suspend fun getPhotos(): Flow<Result<List<PhotoResult>>> {
        return flow {
            emit(getResult { unsplashRemoteDataSource.getPhotos() })
        }
    }
}