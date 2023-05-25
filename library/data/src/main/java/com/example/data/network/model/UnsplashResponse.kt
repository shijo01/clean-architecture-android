package com.example.data.network.model


@kotlinx.serialization.Serializable
data class UnsplashResponseDto(
    val total: Int?,
    val total_pages: Int?,
    val results: List<PhotoResultsDto>?
)