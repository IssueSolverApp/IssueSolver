package com.issuesolver.presentation.home.filter

import BottomBarScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.issuesolver.presentation.common.AuthButton
import com.issuesolver.presentation.home.home.HomeViewModel
import com.issuesolver.presentation.newrequest.DropDownCategory
import com.issuesolver.presentation.newrequest.DropDownOrganization
import com.issuesolver.presentation.newrequest.RequestScreenViewModel

@Composable
fun FilterScreen(
    navController: NavController,
    viewModel: FilterViewModel = hiltViewModel(),
    viewModel2: RequestScreenViewModel = hiltViewModel(),
    viewModel3: HomeViewModel = hiltViewModel(),

    ){
    val listStatus = listOf("Pending", "Reviewing", "Unfounded", "Resolved", "Archived")
//    val listCategories = listOf()
//    val listOrganizations = listOf()
    val listDays = listOf("Last Day", "Last Week", "Last Month")
    var status by remember { mutableStateOf(listStatus.first()) }
//    var categoryName by remember { mutableStateOf(listCategories.first()) }
//    var organizationName by remember { mutableStateOf(listOrganizations.first()) }
    var days by remember { mutableStateOf(listDays.first()) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding()
            .padding(top = 22.dp, bottom = 16.dp)
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
                color = Color.Black,
                modifier = Modifier.padding(bottom = 20.dp)

            )
            Divider(
                thickness = 0.5.dp,
                color = Color(0xFF2981FF),
                modifier = Modifier.padding(end = 7.dp, bottom = 12.dp)
            )
            Column(
            ) {
                DropDownCategory(
                    category = "Kateqoriya",
                    placeHolder = "Problemin Kateqoriyası",
                    viewModel = viewModel2
                )

                DropDownOrganization(
                    category = "Qurum",
                    placeHolder = "Problemin yönləndiriləcəyi qurum",
                    viewModel = viewModel2
                )
                StaticDropDown(
                    category ="Status",
                    placeHolder ="Problemin statusu",
                    list =status
                )
                StaticDropDown(
                    category ="Tarix",
                    placeHolder ="Problemin tarixi",
                    list =days
                )
            }
        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            AuthButton(
                text = "Axtarış et",
                onClick = {
                    viewModel3.loadItems(status = "", categoryName = "", organizationName = "", days = "")
                    navController.navigate(BottomBarScreen.Home.route)                          },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
