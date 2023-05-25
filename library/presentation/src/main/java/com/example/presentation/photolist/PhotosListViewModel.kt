package com.example.presentation.photolist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.model.Result
import com.example.domain.usecases.GetPhotosUseCase
import com.example.presentation.photolist.state.PhotosListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosListViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {
    private val _uiState by lazy { mutableStateOf<PhotosListUiState>(PhotosListUiState.Loading) }
    internal val uiState: State<PhotosListUiState> by lazy { _uiState.apply { loadUiState() } }

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _uiState.value = PhotosListUiState.Error
    }

    private fun loadUiState() {
        viewModelScope.launch(exceptionHandler) {
            _uiState.value = PhotosListUiState.Loading
            getPhotosUseCase().onEach { result ->
                when (result) {
                    is Result.Error -> {
                        _uiState.value = PhotosListUiState.Error
                    }
                    is Result.Success -> {
                        _uiState.value =
                            PhotosListUiState.ShowPhotos(result.data.map { it.url })
                    }
                }
            }.launchIn(this)
        }
    }
}