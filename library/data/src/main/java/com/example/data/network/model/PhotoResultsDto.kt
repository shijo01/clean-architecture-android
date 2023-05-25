package com.example.data.network.model


@kotlinx.serialization.Serializable
data class PhotoResultsDto(
    val alt_description: String?,
    val blur_hash: String?,
    val color: String?,
    val created_at: String?,
    val description: String?,
    val height: Int?,
    val id: String?,
    val promoted_at: String?,
    val updated_at: String?,
    val urls: UrlsDto?,
    val width: Int?
)

