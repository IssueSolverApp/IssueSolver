package com.issuesolver.presentation.profile.profile

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.issuesolver.R
import com.issuesolver.presentation.bottombar.AnimatedNavigationBar
import com.issuesolver.presentation.navigation.Routes


@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileScreenViewModel = hiltViewModel(),
) {



    val uiState by viewModel.uiState.collectAsState()
//    val email = uiState.email != null
//    val fullName = uiState.fullName != null
//    val isEmailError = uiState.emailError != null
//    val isfullNameError = uiState.fullNameError != null
    val forgetPasswordState by viewModel.profileState.collectAsState()


    Scaffold(
        modifier = Modifier
        .navigationBarsPadding(),
        bottomBar = {
            AnimatedNavigationBar()
        },
    content = { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .imePadding()
                .padding(top = 24.dp, start = 20.dp, end = 20.dp, bottom = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
                    .verticalScroll(rememberScrollState())
                ,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color.White)
                        .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 24.dp)
                        .fillMaxWidth()
                        .clickable(onClick = { }),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Row (    verticalAlignment = Alignment.CenterVertically
                    ){
                        Image(
                            painter = painterResource(R.drawable.et_profile_male),
                            contentDescription = "et_profile_male",
                            modifier = Modifier.padding(end=12.dp)
                        )
                        Column {
                            Text(
                                text=uiState.fullName ?: "No Name Available",
                                fontWeight = FontWeight.W600,
                                fontSize = 20.sp,
                                color = Color(0xFF2981FF),
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding()
                            )
                            Text(
                                text=uiState.email ?: "No Email Available",
                                fontSize = 15.sp,
                                color = Color(0xFF9D9D9D),
                                textAlign = TextAlign.Start,
                                modifier = Modifier.padding()
                            )
                        }
                    }
                    Image(
                        painter = painterResource(R.drawable.settings_ic),
                        contentDescription = "settings_ic",
                        modifier = Modifier.clickable(onClick = { navController.navigate(Routes.PROFILE_MY_ACCOUNT)})
                    )
                }
                Row(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color.White)
                        .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 24.dp)
                        .fillMaxWidth()
                        .clickable(onClick = { navController.navigate("profile_new_password") }),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Row ( verticalAlignment = Alignment.CenterVertically){
                        Image(
                            painter = painterResource(R.drawable.pricavy_ic),
                            contentDescription = "settings_ic",
                        )
                        Text(
                            "Şifrəni dəyiş",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            color = Color.Black,
//                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(start=12.dp)
                        )
                    }
                    Image(
                        painter = painterResource(R.drawable.profile_nav_array),
                        contentDescription = "profile_nav_array",
                    )
                }
                Spacer(modifier = Modifier.height(36.dp))
                Row(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color.White)
                        .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 24.dp)
                        .fillMaxWidth()
                        .clickable(onClick = { }),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        "Məxfilik siyasəti",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        color = Color.Black,
                    )
                    Image(
                        painter = painterResource(R.drawable.profile_nav_array),
                        contentDescription = "profile_nav_array",
                    )
                }
                Row(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color.White)
                        .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 24.dp)
                        .fillMaxWidth()
                        .clickable(onClick = { }),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        "Tez-tez verilən suallar",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        color = Color.Black,
                    )
                    Image(
                        painter = painterResource(R.drawable.profile_nav_array),
                        contentDescription = "profile_nav_array",
                    )
                }
                Row(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color.White)
                        .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 24.dp)
                        .fillMaxWidth()
                        .clickable(onClick = { }),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        "Tətbiq haqqında",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        color = Color.Black,
                    )
                    Image(
                        painter = painterResource(R.drawable.profile_nav_array),
                        contentDescription = "profile_nav_array",
                    )
                }
                Spacer(modifier = Modifier.height(36.dp))
                Row(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color.White)
                        .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 24.dp)
                        .fillMaxWidth()
                        .clickable(onClick = { }),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Row( verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(R.drawable.exit_ic),
                            contentDescription = "exit_ic",
                        )
                        Text(
                            "Hesabdan çıxış",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(start=12.dp)
                        )
                    }
                    Image(
                        painter = painterResource(R.drawable.profile_nav_array),
                        contentDescription = "profile_nav_array",
                    )
                }
                Row(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(color = Color.White)
                        .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 24.dp)
                        .fillMaxWidth()
                        .clickable(onClick = { navController.navigate("profile_delete_account") }),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        "Hesabı sil",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                        color = Color(0xFfEF5648),
                    )
                    Image(
                        painter = painterResource(R.drawable.profile_nav_array),
                        contentDescription = "profile_nav_array",
                    )
                }
            }
        }
    })
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewProfileScreen() {
//    ProfileScreen()
//}


