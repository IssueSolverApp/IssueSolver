package com.issuesolver.presentation.home.home

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.issuesolver.R
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.presentation.myrequest.UserCard
import com.issuesolver.presentation.navigation.DetailsScreen


@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    ) {

    val selectedStatus by viewModel.selectedStatus.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val selectedOrganization by viewModel.selectedOrganization.collectAsState()
    val selectedDays by viewModel.selectedDays.collectAsState()

    LaunchedEffect(selectedStatus, selectedCategory, selectedOrganization, selectedDays) {
        viewModel.fetchFilteredRequests(selectedStatus, selectedCategory, selectedOrganization, selectedDays)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
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
                    Row() {
                        Image(
                            painter = painterResource(R.drawable.group),
                            contentDescription = "filter",
                            modifier = Modifier
                                .padding(end = 14.dp)
                                .clickable {
                                    navController.navigate(DetailsScreen.HomeFilterScreen.route)
                                }
                        )
                        Image(
                            painter = painterResource(R.drawable.group__1_),
                            contentDescription = "notifications" )
                    }
                }
            }
            Divider(
                thickness = 0.5.dp,
                color = Color(0xFF2981FF),
                modifier = Modifier.padding(end = 7.dp)
            )



            val moviePagingItems: LazyPagingItems<FilterData> = viewModel.requestsState.collectAsLazyPagingItems()


            LazyColumn {
                items(moviePagingItems.itemCount) { index ->
                    moviePagingItems[index]?.let { filterData ->
                        RequestsCard(
                            fullName = filterData.fullName,
                            status = filterData.status,
                            description = filterData.description,
                            categoryName = filterData.category?.categoryName
                        )
                    }
                }

                moviePagingItems.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item {
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
    }
}





