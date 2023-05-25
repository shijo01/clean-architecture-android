package com.example.data.network.mapper

import com.example.data.network.model.PhotoResultsDto
import com.example.domain.entity.model.PhotoResult

internal fun PhotoResultsDto.mapToDomainModel(): PhotoResult {
    return PhotoResult(
        title = this.description.orEmpty(),
        description = this.alt_description.orEmpty(),
        url = this.urls?.full.orEmpty()
    )
}