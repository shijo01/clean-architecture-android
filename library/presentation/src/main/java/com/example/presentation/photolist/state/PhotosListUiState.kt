package com.example.presentation.photolist.state

internal sealed class PhotosListUiState {
    object Loading : PhotosListUiState()
    object Error : PhotosListUiState()
    data class ShowPhotos(val photoUrls: List<String>) : PhotosListUiState()
}
