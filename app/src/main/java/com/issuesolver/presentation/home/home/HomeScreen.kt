package com.issuesolver.presentation.home.home

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
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalContext
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
import com.issuesolver.common.PlaceholderShimmerCard
import com.issuesolver.common.SnackBar
import com.issuesolver.domain.entity.networkModel.home.FilterData
import com.issuesolver.presentation.myrequest.UserCard
import com.issuesolver.presentation.navigation.DetailsScreen
import kotlinx.coroutines.delay


@Composable
fun HomeScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: TestViewModel = hiltViewModel(),
//    status:String,
//    category:String,
//    organization:String,
//    days:String

    ) {
    val context = LocalContext.current
    val filterPreferences = remember {
        getFilterPreferences(context)
    }

    val categoryName = filterPreferences["categoryName"] ?: ""
    val organizationName = filterPreferences["organizationName"] ?: ""
    val days = filterPreferences["days"] ?: ""
    val status = filterPreferences["status"] ?: ""

//    val status = navController.currentBackStackEntry?.arguments?.getString("status") ?: ""
//    val category = navController.currentBackStackEntry?.arguments?.getString("category") ?: ""
//    val organization = navController.currentBackStackEntry?.arguments?.getString("organization") ?: ""
//    val days = navController.currentBackStackEntry?.arguments?.getString("days") ?: ""



    LaunchedEffect(status, categoryName, organizationName, days) {
        viewModel.filter(status,categoryName, organizationName, days)

        clearFilterPreferences(context)
    }



    val requestResults = viewModel.filterResults.collectAsLazyPagingItems()
    //val filterResults = testViewModel.filterResults.collectAsLazyPagingItems()
    //testViewModel.filter("", "", "", "")
//---------------------------------------------
    val selectedStatus by viewModel.selectedStatus.collectAsState()
    //var status by remember { mutableStateOf(selectedStatus )  }

    //println(filterResults)
    //val requestResults = testViewModel.requestResults.collectAsLazyPagingItems()

    viewModel.request()

//
//        val selectedStatus by viewModel.selectedStatus.collectAsState()
//        val selectedCategory by viewModel.selectedCategory.collectAsState()
//        val selectedOrganization by viewModel.selectedOrganization.collectAsState()
//        val selectedDays by viewModel.selectedDays.collectAsState()

//        LaunchedEffect(Unit) {
//            viewModel.fetchFilteredRequests2()
//        }
//    val snackbarHostState = remember { SnackbarHostState() }
//    val success = navController.currentBackStackEntry
//        ?.savedStateHandle
//        ?.get<Boolean>("requestSuccess") ?: false
//
//    if (success) {
//        LaunchedEffect(key1 = success) {
//            snackbarHostState.showSnackbar("Request sent successfully!")
//            navController.currentBackStackEntry?.savedStateHandle?.remove<Boolean>("requestSuccess")
//        }
//    }
    //val selectedStatus by testViewModel.selectedStatus.collectAsState()
    //val moviePagingItems: LazyPagingItems<FilterData> = viewModel.requestsState.collectAsLazyPagingItems()
    val uiState by viewModel.uiState.collectAsState()

//    LaunchedEffect(Unit) {
//        testViewModel.setFilterParams(TestViewModel.FilterParams()) //Baxılır
//
//    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
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
                modifier = Modifier.padding(bottom = 18.dp)
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(requestResults.itemCount) { index ->
                    requestResults[index]?.let { filterData ->
                        RequestsCard(
                            fullName = filterData.fullName,
                            status = filterData.status,
                            description = filterData.description,
                            categoryName = filterData.category?.categoryName,
                            viewModel=viewModel,
                            requestId = filterData.requestId,
                            likeSuccess = filterData.likeSuccess,
                            onClick = {

                                navController.navigate(DetailsScreen.DetailsById.route+ "/${filterData.requestId}")
                            }
                        )
                    }
                }
                requestResults.apply {
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
            }
        }
    }
}





