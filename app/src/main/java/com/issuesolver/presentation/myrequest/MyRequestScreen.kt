package com.issuesolver.presentation.myrequest

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.issuesolver.domain.entity.networkModel.home.FilterData

@Composable
fun MyRequestScreen(viewModel: MyRequestViewModel = hiltViewModel()) {

    //val lazyPagingItems = viewModel.myRequests.collectAsLazyPagingItems()

    //val moviesState = viewModel.moviesState.collectAsLazyPagingItems()
    val moviePagingItems: LazyPagingItems<FilterData> = viewModel.moviesState.collectAsLazyPagingItems()


    LazyColumn {
        items(moviePagingItems.itemCount) {  item ->
            when (item) {
                null -> {
                    // Показать shimmer effect или индикатор загрузки для элемента
                }
                else -> {
                    // Показать элемент списка
                    UserCard(fullName = moviePagingItems.itemSnapshotList.items.firstOrNull()?.fullName,
                        status = moviePagingItems.itemSnapshotList.items.firstOrNull()?.status,
                        description = moviePagingItems.itemSnapshotList.items.firstOrNull()?.description,
                        categoryName = moviePagingItems.itemSnapshotList.items.firstOrNull()?.category?.categoryName)
                }
            }
        }

        moviePagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        // Показать shimmer effect или индикатор загрузки для всей страницы
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        // Показать индикатор загрузки при подгрузке данных
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = moviePagingItems.loadState.refresh as LoadState.Error
                    item {
                        // Показать сообщение об ошибке
                        Text(text = "Error: ${e.error.localizedMessage}")
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = moviePagingItems.loadState.append as LoadState.Error
                    item {
                        // Показать сообщение об ошибке при подгрузке данных
                        Text(text = "Error: ${e.error.localizedMessage}")
                    }
                }
            }
        }
    }

}