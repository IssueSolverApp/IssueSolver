package com.issuesolver.presentation.home.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.issuesolver.R
import com.issuesolver.common.PlaceholderShimmerCard
import com.issuesolver.common.PlaceholderShimmerCard2
import com.issuesolver.common.SnackBar
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.presentation.myrequest.UserCard
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: TestViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val filterPreferences = remember {
        getFilterPreferences(context)
    }

    val categoryName = filterPreferences["categoryName"] ?: ""
    val organizationName = filterPreferences["organizationName"] ?: ""
    val days = filterPreferences["days"] ?: ""
    val status = filterPreferences["status"] ?: ""

    LaunchedEffect(status, categoryName, organizationName, days) {
        viewModel.filter(status,categoryName, organizationName, days)

        clearFilterPreferences(context)
    }

    val requestResults = viewModel.filterResults.collectAsLazyPagingItems()
    viewModel.request()

    val pullToRefreshState = rememberPullToRefreshState()

    LaunchedEffect(requestResults.loadState) {
        if (pullToRefreshState.isRefreshing && requestResults.loadState.refresh !is LoadState.Loading) {
            Log.d("HomeScreen", "Data refreshed")
            pullToRefreshState.endRefresh()
        }
    }

    LaunchedEffect(pullToRefreshState.isRefreshing) {
        if (pullToRefreshState.isRefreshing) {
            Log.d("HomeScreen", "Data refreshing")
            viewModel.filter(status, categoryName, organizationName, days)
        }
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .nestedScroll(pullToRefreshState.nestedScrollConnection)
            .imePadding()
            .padding(top = 25.dp, start = 20.dp, end = 20.dp, bottom = 25.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
        ) {
            Column(
                Modifier.padding(bottom = 16.dp)
            ) {

//                SnackbarHost(hostState = snackbarHostState) { snackbarData ->
//                    SnackBar(snackbarData = "Sorğunuz uğurla paylaşıldı")
//                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ellipse_9),
                            contentDescription = "ellipse_9",
                            modifier = Modifier.padding(end = 4.dp))
                        Text(
                            "Issuse Solver",
                            style = MaterialTheme.typography.headlineMedium,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.W600,
                            textAlign = TextAlign.Start,
                            color = Color(0xFF2981FF),
                        )
                    }
                    Image(
                        painter = painterResource(R.drawable.group),
                        contentDescription = "filter",
                        modifier = Modifier
                            .padding(end = 14.dp)
                            .clickable {
                                navController.navigate(com.issuesolver.presentation.navigation.HomeScreen.HomeFilterScreen.route)
                            }
                    )


                }
            }
            Divider(
                thickness = 0.5.dp,
                color = Color(0xFF2981FF),
                modifier = Modifier.padding(bottom = 18.dp)
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                if (!pullToRefreshState.isRefreshing) {
                    items(requestResults.itemCount) { index ->
                        requestResults[index]?.let { filterData ->
                            RequestsCard(
                                fullName = filterData.fullName,
                                status = filterData.status,
                                description = filterData.description,
                                categoryName = filterData.category?.categoryName,
                                viewModel = viewModel,
                                requestId = filterData.requestId,
                                likeSuccess = filterData.likeSuccess,
                                onClick = {
                                    navController.navigate(com.issuesolver.presentation.navigation.HomeScreen.DetailsById.route + "/${filterData.requestId}")
                                }
                            )
                        }
                    }
                }

                requestResults.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> { // Display shimmer during loading
                            items(5) {
                                PlaceholderShimmerCard()
                            }
                        }
                        loadState.append is LoadState.Loading -> {
                            item {
                                // Optional: Add a shimmer or loading indicator for more data being loaded
                            }
                        }
                        loadState.refresh is LoadState.Error -> {
                            val e = requestResults.loadState.refresh as LoadState.Error
                            item {
                                Text(text = "Error: ${e.error.localizedMessage}")
                            }
                        }
                        loadState.append is LoadState.Error -> {
                            val e = requestResults.loadState.append as LoadState.Error
                            item {
                                Text(text = "Error: ${e.error.localizedMessage}")
                            }
                        }
                    }
                }

                if (requestResults.itemCount == 0 && requestResults.loadState.refresh is LoadState.NotLoading && requestResults.loadState.append.endOfPaginationReached) {
                    item {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("Müraciətlər yoxdur.",
                                style = TextStyle(
                                    color = Color(0xFF6E6E6E),
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.W400,)
                            )
                        }
                    }
                }
            }
        }
        if (pullToRefreshState.progress>0||pullToRefreshState.isRefreshing) {

            PullToRefreshContainer(
                modifier = Modifier.align(Alignment.TopCenter),
                state = pullToRefreshState,
                containerColor = Color.White,
                contentColor =Color(0xFF2981FF),

                )
        }
    }

}

