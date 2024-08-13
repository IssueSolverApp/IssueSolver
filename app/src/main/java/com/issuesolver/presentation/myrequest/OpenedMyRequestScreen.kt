package com.issuesolver.presentation.myrequest

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.issuesolver.R
import com.issuesolver.common.AlertDialogExample
import com.issuesolver.common.PopUp
import com.issuesolver.common.StatusR
import com.issuesolver.presentation.common.LoadingOverlay
import com.issuesolver.presentation.home.home.StatusColors
import com.issuesolver.presentation.home.home.statusColorMap


@Composable
fun OpenedMyRequestScreen(navController:NavController,  id: String,   viewModel: MyRequestViewModel= hiltViewModel()){


    LaunchedEffect(Unit) {
        viewModel.getRequestById(id.toInt())
    }
    val requestById by viewModel.requestById.collectAsState()
    var isLiked by rememberSaveable { mutableStateOf(requestById?.likeSuccess ?: false) }
    LaunchedEffect(requestById) {
        requestById?.let {
            isLiked = it.likeSuccess!!
        }
    }

    val likeStates by viewModel.likeStates.collectAsState()
    var favoriteState = likeStates[id.toInt()] ?: requestById?.likeSuccess?: false
    val icon = if (favoriteState!!) R.drawable.heart_clicked else R.drawable.heart_default

    val like by viewModel.liked.collectAsState(initial = requestById?.likeSuccess)
    var isLike by rememberSaveable { mutableStateOf(like) }

    val requestByIdState by viewModel.requestByIdState.collectAsState()

    when (requestByIdState?.status) {
        StatusR.LOADING -> {
            Log.d("LoginPage", "Loading state triggered")

        }

        StatusR.SUCCESS -> {

        }

        StatusR.ERROR -> {

        }

        else -> {

        }
    }

    val statusColors = statusColorMap[ requestById?.status] ?: StatusColors(androidx.compose.ui.graphics.Color.Transparent,
        androidx.compose.ui.graphics.Color.Transparent)

    var showDialog by remember { mutableStateOf(false) }
    if (showDialog) {
        if(requestById?.status != "Gözləmədə") {

            AlertDialogExample(
                message = "Sorğular yalnız \"Gözləmədə\" statusunda silinə bilər.",
                onConfirmation = { showDialog = false }
            )
        } else
            PopUp(
                text = "Sorğunuzu silməyə əminsiniz?",
                confirm = "Bəli",
                dismiss = "Xeyr",
                onConfirmation = {
                    id.toInt()?.let { viewModel.deleteRequestById(it)
                        navController.popBackStack()
                    }
                },
                onDismiss = { showDialog = false }
            )
    }

    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        BottomSheet ({
            showSheet = false
        },
            viewModel,
            id.toInt()
        )
    }


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
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Row {
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
                                                Text(
                                                    text = "${
                                                        if (favoriteState!!) {
                                                            requestById?.likeCount?.plus(1)
                                                        } else
                                                            requestById?.likeCount

                                                    }",
                                                    color = Color(0xFF002252),
                                                    fontSize = 10.sp,
                                                    fontWeight = FontWeight.W500
                                                )


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
                                    Row {
                                        IconButton(onClick = {
                                            showDialog = true

                                        }) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.vector),
                                                contentDescription = null,
                                            )
                                        }
                                    }
                                }

                            }

                        }


                    }

                }

            }

        }

    )
}
