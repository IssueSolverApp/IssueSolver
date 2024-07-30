package com.issuesolver.presentation.home.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.issuesolver.R
import com.issuesolver.common.StatusR
import com.issuesolver.presentation.common.LoadingOverlay
import com.issuesolver.presentation.myrequest.BottomSheet
import com.issuesolver.presentation.myrequest.MyRequestViewModel


@Composable
fun DetailedRequestScreen(navController:NavController,  id: String,   viewModel: TestViewModel = hiltViewModel()){


    LaunchedEffect(Unit) {
        viewModel.getRequestById(id.toInt()) // Асинхронный запрос данных
    }
    val requestById by viewModel.requestById.collectAsState()

    var showLoading by remember { mutableStateOf(false) }

    var isLiked by rememberSaveable { mutableStateOf(requestById?.likeSuccess ?: false) }

    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        BottomSheetHome ({
            showSheet = false
        },
            //comments,
            viewModel,
            id.toInt()
        )
    }

    LaunchedEffect(requestById) {
        requestById?.let {
            isLiked = it.likeSuccess!!
        }
    }
    val likeStates by viewModel.likeStates.collectAsState()
    var favoriteState = likeStates[id.toInt()] ?: requestById?.likeSuccess?: false


    val statusColors = statusColorMap[ requestById?.status] ?: StatusColors(androidx.compose.ui.graphics.Color.Transparent,
        androidx.compose.ui.graphics.Color.Transparent)

    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .statusBarsPadding(),
        bottomBar = {
        },

        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
                    .padding(top = 15.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .imePadding()

                ) {
                    Column(
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(100.dp))
                                .background(Color.White)
                                .clickable {
                                    navController.popBackStack()
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.backarray),
                                contentDescription = "Back",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(15.dp))

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            )
                        ) {

                            Column(
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .padding(16.dp)
                            ) {

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()

                                ) {

                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Image(
                                            painter = painterResource(id = R.drawable.et_profile_male), // Replace with your drawable resource
                                            contentDescription = "Profile Image",
                                            modifier = Modifier
                                                .size(32.dp)
                                        )

                                        requestById?.fullName?.let {
                                            Text(
                                                text = it,
                                                color = Color(0xFF2981FF),
                                                modifier = Modifier.padding(start = 6.dp),
                                                fontSize = 15.sp,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                        }
                                    }

                                    Row(
                                        modifier = Modifier
                                            .clip(shape = CircleShape)
                                            .background(color = statusColors.backgroundColor)
                                            .padding(8.dp)
                                            .clickable(onClick = { }),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painter = painterResource(R.drawable.ellipse_9),
                                            contentDescription = "ellipse_9",
                                            modifier = Modifier
                                                .padding(start = 20.dp)
                                                .size(8.dp),
                                            colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(statusColors.textColor)

                                        )
                                        requestById?.status?.let {
                                            Text(
                                                text = it,
                                                color = statusColors.textColor,
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.W400,
                                                modifier = Modifier.padding(start = 8.dp, end = 20.dp)
                                            )
                                        }
                                    }


                                }

                                requestById?.organizationName?.let {
                                    Text(
                                        text = it,
                                        color = Color(0xFF002252),
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        modifier = Modifier.padding(top = 8.dp, bottom = 30.dp)
                                    )
                                }

                                Divider(
                                    thickness = 0.5.dp,
                                    color = Color(0xFFc3dcff),
                                    modifier = Modifier.padding(
                                        bottom = 16.dp
                                    )
                                )


                                requestById?.category?.categoryName?.let {
                                    Text(
                                        text = it,
                                        color = Color(0xFF8C8C8C),
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.W400,
                                        modifier = Modifier
                                            .clip(shape = RoundedCornerShape(50.dp))
                                            .background(color = Color(0xFFF0F4F9))
                                            .padding(
                                                top = 8.dp,
                                                bottom = 8.dp,
                                                start = 11.dp,
                                                end = 11.dp
                                            )
                                    )
                                }


                                requestById?.description?.let {
                                    Text(
                                        text = it,
                                        color = Color(0xFF6E6E6E),
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.W500,
                                        modifier = Modifier
                                            .padding(
                                                top = 8.dp,

                                                )
                                    )
                                }
                                Row (
                                    modifier = Modifier.padding(top=16.dp, bottom = 12.dp)
                                ){
                                    Image(
                                        painter = painterResource(id = R.drawable.location_icon),
                                        contentDescription = "location_icon",
                                        modifier = Modifier
                                            .size(20.dp)
                                    )

                                    requestById?.address?.let {
                                        Text(
                                            text = it,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = Color(0xFF002252),
                                            modifier = Modifier.padding(start = 6.dp),
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.W400
                                        )
                                    }

                                }
                                Row {

                                    Image(
                                        painter = painterResource(id = R.drawable.calendar),
                                        contentDescription = "calendar",
                                        modifier = Modifier
                                            .size(20.dp)
                                    )

                                    requestById?.createDate?.let {
                                        Text(
                                            text = it,
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = Color(0xFF002252),
                                            modifier = Modifier.padding(start = 6.dp),
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.W400
                                        )
                                    }
                                }




                                Divider(
                                    thickness = 0.5.dp,
                                    color = Color(0xFFc3dcff),
                                    modifier = Modifier.padding(
                                        top = 16.dp
                                    )
                                )

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {

                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            IconButton(onClick = {
                                                if (favoriteState!!) {
                                                    viewModel.removeLike(requestId = id.toInt())
                                                } else {
                                                    viewModel.sendLike(requestId = id.toInt())
                                                }
                                                favoriteState = !favoriteState!!

                                            }) {
                                                val icon = if (favoriteState!!) R.drawable.heart_clicked else R.drawable.heart_default

                                                Icon(
                                                    painter = painterResource(id = icon),
                                                    contentDescription = null,
                                                    Modifier.size(20.dp),
                                                    tint = if (favoriteState!!) Color.Red else Color(0xFF002252)
                                                )
                                            }
                                            requestById?.likeCount?.let {
                                                Text(
                                                    text = it.toString(),
                                                    color = Color(0xFF002252),
                                                    fontSize = 10.sp,
                                                    fontWeight = FontWeight.W500
                                                )
                                            }

                                        }

                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {

                                            IconButton(onClick = {
                                                showSheet = true

                                            }) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.coment),
                                                    contentDescription = null,
                                                    Modifier.size(20.dp)

                                                )
                                            }
                                            requestById?.commentCount?.let {
                                                Text(
                                                    text = it.toString(),
                                                    color = Color(0xFF002252),
                                                    fontSize = 10.sp,
                                                    fontWeight = FontWeight.W500
                                                )
                                            }

                                        }

                                    }

                                }

                            }

                        }


                    }

                }



            if(showLoading){
                LoadingOverlay()
            }

        }

    )
}
//@Preview(showBackground = true)
//@Composable
//fun PreviewOpenedMyRequestScreen() {
//    val navController = rememberNavController()
//    OpenedMyRequestScreen(navController)
//}

//
//
//@Composable
//fun OpenedMyRequestScreen(navController: NavController, id: String, viewModel: MyRequestViewModel = hiltViewModel()) {
//    LaunchedEffect(Unit) {
//        viewModel.getRequestById(id.toInt())
//    }
//    val requestById by viewModel.requestById.collectAsState()
//    val likeStates by viewModel.likeStates.collectAsState()
//
//    var isLiked by rememberSaveable { mutableStateOf(false) }
//
//    LaunchedEffect(requestById) {
//        requestById?.let {
//            isLiked = likeStates[it.requestId] ?: it.likeSuccess ?: false
//        }
//    }
//
//    Scaffold(
//        modifier = Modifier
//            .navigationBarsPadding()
//            .statusBarsPadding(),
//        bottomBar = {},
//        content = { padding ->
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .imePadding()
//                    .padding(top = 15.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
//            ) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .imePadding()
//                ) {
//                    Column {
//                        Box(
//                            modifier = Modifier
//                                .size(40.dp)
//                                .clip(RoundedCornerShape(100.dp))
//                                .background(Color.White)
//                                .clickable {
//                                    navController.popBackStack()
//                                },
//                            contentAlignment = Alignment.Center
//                        ) {
//                            Image(
//                                painter = painterResource(R.drawable.backarray),
//                                contentDescription = "Back",
//                                modifier = Modifier.size(24.dp)
//                            )
//                        }
//
//                        Card(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .wrapContentHeight(),
//                            colors = CardDefaults.cardColors(
//                                containerColor = Color.White
//                            )
//                        ) {
//                            Column(
//                                modifier = Modifier
//                                    .wrapContentHeight()
//                                    .padding(16.dp)
//                            ) {
//                                // Other UI elements here
//
//
//                                Row(
//                                    verticalAlignment = Alignment.CenterVertically,
//                                    horizontalArrangement = Arrangement.SpaceBetween,
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//
//                                ) {
//
//                                    Row(verticalAlignment = Alignment.CenterVertically) {
//                                        Image(
//                                            painter = painterResource(id = R.drawable.et_profile_male), // Replace with your drawable resource
//                                            contentDescription = "Profile Image",
//                                            modifier = Modifier
//                                                .size(32.dp)
//                                        )
//
//                                        requestById?.fullName?.let {
//                                            Text(
//                                                text = it,
//                                                style = MaterialTheme.typography.bodyMedium,
//                                                color = Color(0xFF2981FF),
//                                                modifier = Modifier.padding(start = 6.dp),
//                                                fontSize = 15.sp,
//                                                fontWeight = FontWeight.W400
//                                            )
//                                        }
//                                    }
//
//                                    Row(
//                                        modifier = Modifier
//                                            .clip(shape = CircleShape)
//                                            .background(color = Color(0xFFc8dcfc))
//                                            .padding(8.dp)
//                                            .clickable(onClick = { }),
//                                        horizontalArrangement = Arrangement.SpaceBetween,
//                                        verticalAlignment = Alignment.CenterVertically
//                                    ) {
//                                        Image(
//                                            painter = painterResource(R.drawable.ellipse_9),
//                                            contentDescription = "ellipse_9",
//                                            modifier = Modifier
//                                                .padding(start = 20.dp)
//                                                .size(8.dp)
//                                        )
//                                        requestById?.status?.let {
//                                            Text(
//                                                text = it,
//                                                color = Color(0xFF0169FE),
//                                                fontSize = 13.sp,
//                                                fontWeight = FontWeight.W400,
//                                                modifier = Modifier.padding(start = 8.dp, end = 20.dp)
//                                            )
//                                        }
//                                    }
//
//
//                                }
//
//                                requestById?.organizationName?.let {
//                                    Text(
//                                        text = it,
//                                        color = Color(0xFF002252),
//                                        fontSize = 15.sp,
//                                        fontWeight = FontWeight.W500,
//                                        modifier = Modifier.padding(top = 8.dp, bottom = 39.dp)
//                                    )
//                                }
//
//                                Divider(
//                                    thickness = 0.5.dp,
//                                    color = Color(0xFFc3dcff),
//                                    modifier = Modifier.padding(
//                                        top = 8.dp,
//                                        bottom = 16.dp
//                                    )
//                                )
//
//
//                                requestById?.category?.categoryName?.let {
//                                    Text(
//                                        text = it,
//                                        color = Color(0xFF8C8C8C),
//                                        fontSize = 13.sp,
//                                        fontWeight = FontWeight.W400,
//                                        modifier = Modifier
//                                            .clip(shape = RoundedCornerShape(50.dp))
//                                            .background(color = Color(0xFFF0F4F9))
//                                            .padding(
//                                                top = 8.dp,
//                                                bottom = 8.dp,
//                                                start = 11.dp,
//                                                end = 11.dp
//                                            )
//                                    )
//                                }
//
//
//                                requestById?.description?.let {
//                                    Text(
//                                        text = it,
//                                        color = Color(0xFF6E6E6E),
//                                        fontSize = 15.sp,
//                                        fontWeight = FontWeight.W500,
//                                        modifier = Modifier
//                                            .padding(
//                                                top = 8.dp,
//
//                                                )
//                                    )
//                                }
//                                Row (
//                                    modifier = Modifier.padding(top=16.dp, bottom = 12.dp)
//                                ){
//                                    Image(
//                                        painter = painterResource(id = R.drawable.location_icon),
//                                        contentDescription = "location_icon",
//                                        modifier = Modifier
//                                            .size(20.dp)
//                                    )
//
//                                    requestById?.address?.let {
//                                        Text(
//                                            text = it,
//                                            style = MaterialTheme.typography.bodyMedium,
//                                            color = Color(0xFF2981FF),
//                                            modifier = Modifier.padding(start = 6.dp),
//                                            fontSize = 13.sp,
//                                            fontWeight = FontWeight.W400
//                                        )
//                                    }
//
//                                }
//                                Row {
//
//                                    Image(
//                                        painter = painterResource(id = R.drawable.calendar),
//                                        contentDescription = "calendar",
//                                        modifier = Modifier
//                                            .size(20.dp)
//                                    )
//
//                                    requestById?.createDate?.let {
//                                        Text(
//                                            text = it,
//                                            style = MaterialTheme.typography.bodyMedium,
//                                            color = Color(0xFF002252),
//                                            modifier = Modifier.padding(start = 6.dp),
//                                            fontSize = 13.sp,
//                                            fontWeight = FontWeight.W400
//                                        )
//                                    }
//                                }
//
//
//
//
//                                Divider(
//                                    thickness = 0.5.dp,
//                                    color = Color(0xFFc3dcff),
//                                    modifier = Modifier.padding(
//                                        top = 16.dp, bottom = 12.dp
//                                    )
//                                )
//
//                                Row(
//                                    horizontalArrangement = Arrangement.SpaceBetween,
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                ) {
//                                    Row {
//                                        Row {
//                                            IconButton(onClick = {
//                                                if (isLiked) {
//                                                    viewModel.removeLike(requestId = id.toInt())
//                                                } else {
//                                                    viewModel.sendLike(requestId = id.toInt())
//                                                }
//                                                isLiked = !isLiked
//                                            }) {
//                                                val icon = if (isLiked) R.drawable.heart_clicked else R.drawable.heart_default
//                                                Icon(
//                                                    painter = painterResource(id = icon),
//                                                    contentDescription = null,
//                                                    Modifier.size(20.dp),
//                                                    tint = androidx.compose.ui.graphics.Color.Red
//                                                )
//                                            }
//                                            // Other buttons here
//                                        }
//
//                                        Column(
//                                            verticalArrangement = Arrangement.Center,
//                                            horizontalAlignment = Alignment.CenterHorizontally
//                                        ) {
//
//                                            IconButton(onClick = {  }) {
//                                                Icon(
//                                                    painter = painterResource(id = R.drawable.coment),
//                                                    contentDescription = null,
//                                                    Modifier.size(20.dp)
//
//                                                )
//                                            }
//                                            requestById?.commentCount?.let {
//                                                Text(
//                                                    text = it.toString(),
//                                                    color = Color(0xFF002252),
//                                                    modifier = Modifier.padding(top = 4.dp),
//                                                    fontSize = 10.sp,
//                                                    fontWeight = FontWeight.W500
//                                                )
//                                            }
//
//                                        }
//
//                                    }
//                                    Row {
//                                        IconButton(onClick = {  }) {
//                                            Icon(
//                                                painter = painterResource(id = R.drawable.vector),
//                                                contentDescription = null,
//                                            )
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    )
//}
//
//
//
