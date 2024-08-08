package com.issuesolver.presentation.home.filter

import BottomBarScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.issuesolver.R
import com.issuesolver.presentation.common.AuthButton
import com.issuesolver.presentation.home.home.FilterViewModel
import com.issuesolver.presentation.home.home.TestViewModel

@Composable
fun FilterScreen(
    navController: NavController,
    viewModel: FilterViewModel = hiltViewModel(),
    testViewModel: TestViewModel = hiltViewModel()
    ){

    val listStatus = listOf("Gözləmədə", "Baxılır", "Əsassızdır", "Həlledildi", "Arxivdədir")
    val listDays = listOf("Son bir gün", "Son bir həftə", "Son bir ay")
   /* val listCategories = viewModel.category.collectAsState(initial = emptyList()).value
    val listOrganizations = viewModel.organization.collectAsState(initial = emptyList()).value

    var status by remember { mutableStateOf(listStatus.firstOrNull()?.toString() ?: "")  }
    var categoryName by remember { mutableStateOf(listCategories?.firstOrNull()?.toString() ?: "") }
    var organizationName by remember { mutableStateOf(listOrganizations?.firstOrNull()?.toString() ?: "") }
    var days by remember { mutableStateOf(listDays.firstOrNull()?.toString() ?: "")  }


    val selectedStatus by viewModel.selectedStatus.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val selectedOrganization by viewModel.selectedOrganization.collectAsState()
    val selectedDays by viewModel.selectedDays.collectAsState()

*/
    val context = LocalContext.current


    var categoryName by remember { mutableStateOf("") }
    var organizationName by remember { mutableStateOf( "") }
    var days by remember { mutableStateOf( "")  }
    var status by remember { mutableStateOf("") }




    //val filterResults = testViewModel.filterResults.collectAsLazyPagingItems()
//
//    LaunchedEffect(categoryName) {
//        testViewModel.setFilterParams(TestViewModel.FilterParams(status = categoryName)) //Baxılır
//    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding()
            .padding(top = 22.dp, bottom = 16.dp, start = 20.dp, end = 20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
        ) {
            Text(
                "Filter",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 28.sp,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Start,
                color = Color(0xFF2981FF),
                modifier = Modifier.padding(bottom = 20.dp,top=24.dp)

            )
            Divider(
                thickness = 0.5.dp,
                color = Color(0xFF2981FF),
                modifier = Modifier.padding(end = 7.dp, bottom = 12.dp)
            )
            Column(
            ) {
                CategoryDropDown(
                    category = "Kateqoriya",
                    placeHolder = "Problemin Kateqoriyası",
                    viewModel = viewModel,
                    selectedCategory = { selectedCategory ->
                        categoryName = selectedCategory
                    }
                )
                OrganizationDropDown(
                    category = "Qurum",
                    placeHolder = "Problemin yönləndiriləcəyi qurum",
                    viewModel = viewModel,
                    selectedCategory = { selectedCategory ->
                        organizationName = selectedCategory
                    }
                )
                StaticDropDownStatus(
                    category ="Status",
                    placeHolder ="Problemin statusu",
                    list = listStatus,
                    viewModel=testViewModel,
                    selectedCategory = { selectedCategory ->
                        status = selectedCategory
                    }

                )
                StaticDropDownDays(
                    category ="Tarix",
                    placeHolder ="Problemin tarixi",
                    list = listDays,
                    viewModel=viewModel,
                    selectedCategory = { selectedCategory ->
                        days = selectedCategory
                    }
                )
            }
        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            AuthButton(
                text = "Axtarış et",
                onClick = {
                    //viewModel.applyFilters(status, categoryName, organizationName, days)
//                    viewModel.fetchFilteredRequests(status = selectedStatus, categoryName = selectedCategory, organizationName = selectedOrganization, days = selectedDays)
                    //viewModel.applyFilters2(selectedStatus, selectedCategory, selectedOrganization, selectedDays)
                    //viewModel.applyFilters2("", categoryName, "", "")


                    saveFilterPreferences(context, categoryName, organizationName, days, status)


//                    testViewModel.filter(status, categoryName, organizationName, days )

                        navController.navigate(BottomBarScreen.Home.route)
                    //+ "/${status}"+ "/${categoryName}"+ "/${organizationName}"+ "/${days}"
//                    navController.navigate(
//                        "${BottomBarScreen.Home.route}/$categoryName/$organizationName/$status/$days"
//                    )
                },
                modifier = Modifier.fillMaxWidth(),
            )

        }
    }
}
