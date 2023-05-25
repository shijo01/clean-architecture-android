package com.example.presentation.photolist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.presentation.photolist.state.PhotosListUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.launch

@Composable
fun UnsplashPhotosList(
    modifier: Modifier = Modifier,
    photosListViewModel: PhotosListViewModel
) {
    val uiState by photosListViewModel.uiState
    PhotosListContent(modifier = modifier, uiState = uiState)

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun PhotosListContent(modifier: Modifier, uiState: PhotosListUiState) {

    Scaffold { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (uiState) {
                PhotosListUiState.Error -> {
                    CircularProgressIndicator()
                }
                PhotosListUiState.Loading -> {
                    Text(text = "Error")
                }
                is PhotosListUiState.ShowPhotos -> {
                    VerticalPager(pageCount = uiState.photoUrls.size) {
                        PhotoItem(modifier = Modifier.fillMaxSize(), url = uiState.photoUrls[it])
                    }
                }
            }
        }
    }
}

fun LazyListState.disableScrolling(scope: CoroutineScope) {
    scope.launch {
        scroll(scrollPriority = MutatePriority.PreventUserInput) {
            // Await indefinitely, blocking scrolls
            awaitCancellation()
        }
    }
}

fun LazyListState.reenableScrolling(scope: CoroutineScope) {
    scope.launch {
        scroll(scrollPriority = MutatePriority.PreventUserInput) {
            // Do nothing, just cancel the previous indefinite "scroll"
        }
    }
}

@Composable
internal fun PhotoItem(
    modifier: Modifier = Modifier,
    url: String
) {
    Box(modifier = modifier.fillMaxSize()) {
        SubcomposeAsyncImage(
            model = ImageRequest
                .Builder(LocalContext.current)
                .data(url)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .diskCachePolicy(CachePolicy.ENABLED)
                .build(),
            contentDescription = null,
            loading = {
                CircularProgressIndicator()
            },
            contentScale = ContentScale.Crop
        )
    }

}

//Photos list backup
/*
val state = rememberLazyListState()
                    val scope = rememberCoroutineScope()
                    var currentPosition by remember {
                        mutableStateOf(0)
                    }
                    val pxValue =
                        with(LocalDensity.current) { LocalConfiguration.current.screenHeightDp.dp.toPx() }

                    if (state.firstVisibleItemScrollOffset > 200) {
                        //state.disableScrolling(scope)
                        Log.e(
                            "PhotosListContent",
                            "PhotosListContent: ${state.firstVisibleItemScrollOffset} "
                        )
                        LaunchedEffect(key1 = true, block = {

                            state.animateScrollToItem(
                                currentPosition + 1
                            )
                            currentPosition += 1
                            //state.reenableScrolling(scope)
                        })
                    }

                    LazyColumn(state = state) {
                        items(uiState.photoUrls) { url ->
                            PhotoItem(modifier = Modifier.fillParentMaxSize(), url = url)
                        }
                    }
*/