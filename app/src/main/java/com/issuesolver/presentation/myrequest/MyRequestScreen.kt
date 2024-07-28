package com.issuesolver.presentation.myrequest

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.presentation.navigation.DetailsScreen

@Composable
fun MyRequestScreen(navController: NavController,
    viewModel: MyRequestViewModel = hiltViewModel()
) {

//    LaunchedEffect {
//        viewModel.getMovies()
//    }

    LaunchedEffect(Unit) {
         viewModel.getMovies() // Асинхронный запрос данных
    }

    //val lazyPagingItems = viewModel.myRequests.collectAsLazyPagingItems()

    //val moviesState = viewModel.moviesState.collectAsLazyPagingItems()
    val moviePagingItems: LazyPagingItems<FilterData> = viewModel.moviesState.collectAsLazyPagingItems()


    LazyColumn {
        items(moviePagingItems.itemCount) { index ->
            moviePagingItems[index]?.let { filterData ->
                UserCard(
                    fullName = filterData.fullName,
                    status = filterData.status,
                    description = filterData.description,
                    categoryName = filterData.category.categoryName,
                    viewModel = viewModel,
                    requestId = filterData.requestId,
                    likeSuccess=filterData.likeSuccess,
                    onClick = {

                        //navController.navigate("requestDetail/${filterData.requestId}")
                        navController.navigate(DetailsScreen.RequestById.route+ "/${filterData.requestId}")
                    }
                )
            }
        }

        // Include your loading and error handling as before
        moviePagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        // Display loading state
                    }
                }
                loadState.append is LoadState.Loading -> {
                    item {
                        // Display loading at the end of the list
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = moviePagingItems.loadState.refresh as LoadState.Error
                    item {
                        Text(text = "Error: ${e.error.localizedMessage}")
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = moviePagingItems.loadState.append as LoadState.Error
                    item {
                        Text(text = "Error: ${e.error.localizedMessage}")
                    }
                }
            }
        }
    }





//    LazyColumn {
//        items(moviePagingItems.itemCount) {  item ->
//            when (item) {
//                null -> {
//                    // Показать shimmer effect или индикатор загрузки для элемента
//                }
//                else -> {
//                    // Показать элемент списка
//                    UserCard(fullName = moviePagingItems.itemSnapshotList.items.firstOrNull()?.fullName,
//                        status = moviePagingItems.itemSnapshotList.items.firstOrNull()?.status,
//                        description = moviePagingItems.itemSnapshotList.items.firstOrNull()?.description,
//                        categoryName = moviePagingItems.itemSnapshotList.items.firstOrNull()?.category?.categoryName,
//                        viewModel= viewModel)
//                }
//            }
//        }
//
//        moviePagingItems.apply {
//            when {
//                loadState.refresh is LoadState.Loading -> {
//                    item {
//                        // Показать shimmer effect или индикатор загрузки для всей страницы
//                    }
//                }
//                loadState.append is LoadState.Loading -> {
//                    item {
//                        // Показать индикатор загрузки при подгрузке данных
//                    }
//                }
//                loadState.refresh is LoadState.Error -> {
//                    val e = moviePagingItems.loadState.refresh as LoadState.Error
//                    item {
//                        // Показать сообщение об ошибке
//                        Text(text = "Error: ${e.error.localizedMessage}")
//                    }
//                }
//                loadState.append is LoadState.Error -> {
//                    val e = moviePagingItems.loadState.append as LoadState.Error
//                    item {
//                        // Показать сообщение об ошибке при подгрузке данных
//                        Text(text = "Error: ${e.error.localizedMessage}")
//                    }
//                }
//            }
//        }
//    }

}