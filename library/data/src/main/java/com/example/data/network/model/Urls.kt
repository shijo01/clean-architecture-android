package com.example.data.network.model

@kotlinx.serialization.Serializable
data class UrlsDto(
    val full: String?,
    val raw: String?,
    val regular: String?,
    val small: String?,
    val small_s3: String?,
    val thumb: String?
)