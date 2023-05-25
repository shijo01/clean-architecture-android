package com.example.data.datasource

import com.example.data.network.UnsplashApi
import com.example.data.network.mapper.mapToDomainModel
import com.example.domain.entity.model.PhotoResult
import javax.inject.Inject

internal class UnsplashRemoteDataSourceImpl @Inject constructor(
    private val unsplashApi: UnsplashApi
) : UnsplashRemoteDataSource {
    override suspend fun getPhotos(): List<PhotoResult> {
        return unsplashApi.getPhotos().results?.map { it.mapToDomainModel() } ?: emptyList()
    }

}