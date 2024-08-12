package com.issuesolver.presentation.myrequest

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.issuesolver.common.PlaceholderShimmerCard
import com.issuesolver.domain.entity.networkModel.home.FilterData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyRequestScreen(navController: NavController,
                    paddingValues: PaddingValues,
                    viewModel: MyRequestViewModel = hiltViewModel()
) {



    LaunchedEffect(Unit) {
         viewModel.getMovies()
    }

        viewModel.loadComments(202)

    val moviePagingItems: LazyPagingItems<FilterData> = viewModel.moviesState.collectAsLazyPagingItems()
    val pullToRefreshState = rememberPullToRefreshState()

    LaunchedEffect(moviePagingItems.loadState) {
        if (pullToRefreshState.isRefreshing && moviePagingItems.loadState.refresh !is LoadState.Loading) {
            Log.d("MyRequestScreen", "Data refreshed")
            pullToRefreshState.endRefresh()
        }
    }

    LaunchedEffect(pullToRefreshState.isRefreshing) {
        if (pullToRefreshState.isRefreshing) {
            Log.d("MyRequestScreen", "Data refreshing")
            viewModel.getMovies()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .imePadding()
            .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 25.dp)
            .nestedScroll(pullToRefreshState.nestedScrollConnection)

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

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                if (!pullToRefreshState.isRefreshing) {
                    items(moviePagingItems.itemCount) { index ->
                        moviePagingItems[index]?.let { filterData ->
                            val like by viewModel.liked.collectAsState(initial = filterData.likeSuccess)
                            var isLiked by remember { mutableStateOf(like) }

                            UserCard(
                                fullName = filterData.fullName,
                                status = filterData.status,
                                description = filterData.description,
                                categoryName = filterData.category.categoryName,
                                viewModel = viewModel,
                                requestId = filterData.requestId,
                                likeSuccess = filterData.likeSuccess,
                                onClick = {
                                    navController.navigate(
                                        com.issuesolver.presentation.navigation.MyRequestScreen.RequestById.route + "/${filterData.requestId}"
                                    )
                                }
                            )

                            LaunchedEffect(key1 = filterData.requestId) {
                                viewModel.loadComments(filterData.requestId)
                            }
                        }
                    }
                }

                if (moviePagingItems.itemCount == 0 &&
                    moviePagingItems.loadState.refresh is LoadState.NotLoading &&
                    moviePagingItems.loadState.append.endOfPaginationReached
                ) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                "Sorğunuz yoxdur.",
                                style = TextStyle(
                                    color = Color(0xFF6E6E6E),
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.W400
                                )
                            )
                        }
                    }
                }

                moviePagingItems.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            items(5) {
                                PlaceholderShimmerCard()
                            }
                        }
                        loadState.append is LoadState.Loading -> {
                            item {
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
        }
        if (pullToRefreshState.progress > 0 || pullToRefreshState.isRefreshing) {
            PullToRefreshContainer(
                modifier = Modifier.align(Alignment.TopCenter),
                state = pullToRefreshState,
                containerColor = Color.White,
                contentColor = Color(0xFF2981FF)
            )
        }
    }
}