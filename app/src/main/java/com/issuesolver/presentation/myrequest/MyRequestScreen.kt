package com.issuesolver.presentation.myrequest

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.issuesolver.R
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.presentation.navigation.DetailsScreen

@Composable
fun MyRequestScreen(navController: NavController,
                    viewModel: MyRequestViewModel = hiltViewModel()) {

    //val lazyPagingItems = viewModel.myRequests.collectAsLazyPagingItems()

    //val moviesState = viewModel.moviesState.collectAsLazyPagingItems()
    val moviePagingItems: LazyPagingItems<FilterData> = viewModel.moviesState.collectAsLazyPagingItems()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding()
            .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 25.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
        ) {
            Text(
                "Mənim sorğularım",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 28.sp,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Start,
                color = Color(0xFF2981FF),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Divider(
                thickness = 0.5.dp,
                color = Color(0xFF2981FF),
                modifier = Modifier.padding(bottom = 27.dp)
            )

            LazyColumn (
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ){
                items(moviePagingItems.itemCount) { item ->
                    when (item) {
                        null -> {
                            // Показать shimmer effect или индикатор загрузки для элемента
                        }

                        else -> {
                            // Показать элемент списка
                            UserCard(
                                fullName = moviePagingItems.itemSnapshotList.items.firstOrNull()?.fullName,
                                status = moviePagingItems.itemSnapshotList.items.firstOrNull()?.status,
                                description = moviePagingItems.itemSnapshotList.items.firstOrNull()?.description,
                                categoryName = moviePagingItems.itemSnapshotList.items.firstOrNull()?.category?.categoryName
                            )
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
    }
}