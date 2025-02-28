package com.issuesolver.presentation.newrequest

import BottomBarScreen
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.issuesolver.common.SnackBar
import com.issuesolver.common.StatusR
import com.issuesolver.presentation.common.LoadingOverlay
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("SuspiciousIndentation", "CoroutineCreationDuringComposition")
@Composable
fun RequestScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: RequestScreenViewModel = hiltViewModel(),


) {

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()


    LaunchedEffect(Unit) {
        scope.launch {
            delay(1500)
            snackbarHostState.currentSnackbarData?.dismiss()
        }
    }



    val isFormValid by viewModel.isFormValid.collectAsState()
    val deleteAccountState by viewModel.newRequestState.collectAsState()
    when(deleteAccountState?.status) {

        StatusR.LOADING -> {

            LoadingOverlay()

        }

        StatusR.ERROR -> {
//            Toast.makeText(LocalView.current.context, "Kodun ishlemir X(", Toast.LENGTH_SHORT)
//                .show()


        }

        StatusR.SUCCESS -> {

            scope.launch {
                scrollState.animateScrollTo(0)  // Make sure this executes correctly
                viewModel.resetFields()
                snackbarHostState.showSnackbar("Sorğunuz uğurla paylaşıldı")
                navController.navigate(BottomBarScreen.Home.route)
            }



//            navController.currentBackStackEntry?.savedStateHandle?.set("requestSuccess", true)
//            navController.navigate(BottomBarScreen.Home.route) {
//                popUpTo(BottomBarScreen.Home.route) { inclusive = true }
//            }//            viewModel.clearLoginState()
        }

        else -> {

        }
    }

//    Scaffold { padding ->


    Box(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .padding(paddingValues)
            .padding(top = 7.dp, start = 20.dp, end = 20.dp, bottom = 16.dp)
    ) {

        Column(
            Modifier
                .imePadding()
                .verticalScroll(rememberScrollState())
        ) {

            SnackbarHost(snackbarHostState) { data ->
                SnackBar("Sorğunuz uğurla paylaşıldı", snackbarHostState)
            }

            Navigation(navController)

            Divider(
                thickness = 0.5.dp,
                color = Color(0xFF2981FF),
                modifier = Modifier.padding(
                    bottom = 12.dp
                )
            )


            AddLocation(viewModel)

            DropDownCategory(
                category = "Kateqoriya",
                placeHolder = "Problemin Kateqoriyası",
                viewModel = viewModel
            )

            DropDownOrganization(
                category = "Qurum",
                placeHolder = "Problemin yönləndiriləcəyi qurum",
                viewModel = viewModel
            )

            Description(viewModel)

            Column(
                modifier = Modifier
            ) {
                Button(
                    onClick = {
                        viewModel.sendRequest()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (isFormValid) Color(0xFF2981FF) else Color(0xFF9AC2FB),
                        disabledContainerColor = Color(0xFF9AC2FB)
                    ),
                    enabled = isFormValid
                ) {
                    Text(
                        text = "Paylaş",
                        fontWeight = FontWeight.W500,
                        fontSize = 15.sp,
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(top = 14.dp, bottom = 14.dp)
                    )
                }

                Button(
                    onClick = {
                        viewModel.resetFields()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 10.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFFFFF),
                        disabledContainerColor = Color(0xFFFFFFFF)
                    )
                ) {
                    Text(
                        text = "Sıfırla",
                        fontWeight = FontWeight.W500,
                        fontSize = 15.sp,
                        color = Color(0xFF2981FF),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(top = 14.dp, bottom = 14.dp)
                    )
                }
            }
        }


        //}
    }
}

//
//@Composable
//fun RequestScreen() {
//    val suggestions = listOf("Kotlin", "Java", "Dart", "Python")
//
//    Scaffold { padding ->
//
//        Box(
//            Modifier
//                .statusBarsPadding()
//                .fillMaxSize()
//                .navigationBarsPadding()
//        ) {
//            ConstraintLayout(Modifier.fillMaxSize()) {
//                val (content, buttons) = createRefs()
//
//                Column(
//                    modifier = Modifier
//                        .verticalScroll(rememberScrollState())
//                        .constrainAs(content) {
//                            top.linkTo(parent.top)
//                            bottom.linkTo(buttons.top)
//                            height = Dimension.fillToConstraints
//                        }
//                ) {
//                    Navigation()
//
//                    Divider(
//                        thickness = 0.5.dp,
//                        color = Color(0xFF2981FF),
//                        modifier = Modifier.padding(
//                            start = 20.dp,
//                            end = 20.dp,
//                            top = 20.dp,
//                            bottom = 16.dp
//                        )
//                    )
//
//                    AddLocation()
//
//                    DropDownMenu(
//                        category = "Problemin Kateqoriyası",
//                        placeHolder = "Kateqoriya",
//                        list = suggestions
//                    )
//
//                    DropDownMenu(
//                        category = "Problemin yönləndiriləcəyi qurum",
//                        placeHolder = "Qurum",
//                        list = suggestions
//                    )
//
//                    Description()
//                }
//
//                Column(
//                    modifier = Modifier
//                        .constrainAs(buttons) {
//                            bottom.linkTo(parent.bottom)
//                        }
//                        .imePadding()
//                        .background(Color(0xFFF0F4F9))
//                ) {
//                    Button(
//                        onClick = { },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(top = 8.dp, start = 20.dp, end = 20.dp),
//                        shape = RoundedCornerShape(50.dp),
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = Color(0xFF2981FF),
//                            disabledContainerColor = Color(0xFF9AC2FB)
//                        )
//                    ) {
//                        Text(
//                            text = "Paylaş",
//                            fontWeight = FontWeight.W500,
//                            fontSize = 15.sp,
//                            color = Color.White,
//                            modifier = Modifier
//                                .align(Alignment.CenterVertically)
//                                .padding(top = 14.dp, bottom = 14.dp)
//                        )
//                    }
//
//                    Button(
//                        onClick = { },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(top = 16.dp, start = 20.dp, end = 20.dp, bottom = 10.dp),
//                        shape = RoundedCornerShape(50.dp),
//                        colors = ButtonDefaults.buttonColors(
//                            containerColor = Color(0xFFFFFFFF),
//                            disabledContainerColor = Color(0xFFFFFFFF)
//                        )
//                    ) {
//                        Text(
//                            text = "Sıfırla",
//                            fontWeight = FontWeight.W500,
//                            fontSize = 15.sp,
//                            color = Color(0xFF2981FF),
//                            modifier = Modifier
//                                .align(Alignment.CenterVertically)
//                                .padding(top = 14.dp, bottom = 14.dp)
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
