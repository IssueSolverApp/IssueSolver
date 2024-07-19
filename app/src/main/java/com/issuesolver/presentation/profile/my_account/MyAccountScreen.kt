package com.issuesolver.presentation.profile.my_account

import BottomBarScreen
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.issuesolver.R
import com.issuesolver.common.StatusR
import com.issuesolver.domain.entity.networkModel.profile.UpdateFullNameRequest
import com.issuesolver.domain.entity.networkModel.profile.UpdatePasswordRequest
import com.issuesolver.presentation.bottombar.AnimatedNavigationBar
import com.issuesolver.presentation.common.AuthButton
import com.issuesolver.presentation.common.ErrorText
import com.issuesolver.presentation.common.LoadingOverlay
import com.issuesolver.presentation.profile.enter_password.DeleteAccountEvent
import com.issuesolver.presentation.profile.new_password.NewPasswordScreenEvent
import com.issuesolver.presentation.profile.profile.ProfileScreenState
import com.issuesolver.presentation.profile.profile.ProfileScreenViewModel

@Composable
fun MyAccountScreen(
    navController: NavController,
    viewModel: MyAccountViewModel  = hiltViewModel(),
    ){

    val uiState by viewModel.uiState.collectAsState()
    val updateFullNameState by viewModel.profileState.collectAsState()
    when(updateFullNameState?.status){

        StatusR.LOADING -> {

            LoadingOverlay()

        }

        StatusR.ERROR -> {
            Toast.makeText(LocalView.current.context, "Kodun ishlemir X(", Toast.LENGTH_SHORT).show()


        }
        StatusR.SUCCESS -> {
//            Toast.makeText(LocalView.current.context, "Full Name Changed <3", Toast.LENGTH_SHORT).show()
//            navController.navigate(BottomBarScreen.Profile.route)
//            viewModel.clearLoginState()
        }
        else-> {

        }


    }

    Scaffold(
        modifier = Modifier
            .navigationBarsPadding(),
        bottomBar = {},
        content = { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .imePadding()
                .padding(top = 13.dp, start = 20.dp, end = 20.dp, bottom = 34.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column() {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .background(Color.White)
                            .clickable { navController.popBackStack() },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.backarray),
                            contentDescription = "Back",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Text(
                        "Hesabım",
                        style = MaterialTheme.typography.headlineMedium,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Start,
                        color = Color(0xFF2981FF),
                        modifier = Modifier.padding(top = 24.dp),
                    )
                    Text(
                        "Hesabın məlumatlarını dəyişə bilərsiniz",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Start,
                        color = Color(0xFF9D9D9D),
                        modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
                    )
                }
                Divider(
                    thickness = 0.5.dp,
                    color = Color(0xFF2981FF)
                )
                Column(
                    Modifier
                        .padding(top = 24.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        "Ad, soyad",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 15.sp,
                        color = if (
                            false
//                            isFullNameError
                            ) Color.Red else Color.Black,
                        )
                    TextField(
                        shape = RoundedCornerShape(12.dp),
                        value = uiState.fullName,
                        onValueChange = {
                            viewModel.handleEvent(MyAccountEvent.FullNameChanged(it))
                        },
                        placeholder = {
                            Text(
                                ("Ad, soyad"),
                                color = if (
                                    false
//                                    isFullNameError
                                    ) Color.Red else Color.Gray
                            )
                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .then(
                                if (
                                    false
//                                    isFullNameError
                                ) Modifier.border(
                                    1.dp,
                                    Color.Red,
                                    RoundedCornerShape(12.dp)
                                )
                                else Modifier.border(
                                    1.dp,
                                    Color.White,
                                    RoundedCornerShape(12.dp)
                                )
                            ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        colors = TextFieldDefaults.colors(
                            disabledTextColor = Color(0xFF2981FF),
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            disabledContainerColor = Color.White,
                            errorContainerColor = Color.White,
                            cursorColor = Color(0xFF2981FF),
                            errorCursorColor = Color.Red,
                            focusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor=Color.Transparent,
                            unfocusedIndicatorColor =Color.Transparent


                        )
                    )

                    ErrorText(
                        errorMessage = uiState.fullNameError,
//                        isVisible = isPasswordError
                    )
                }
                Column(Modifier.padding(top = 20.dp)) {
                    Text(
                        text = "E-poçt",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 15.sp,
                        color = if (
                            false
//                            isEmailError
                            ) Color.Red else Color.Black,
                    )
                    TextField(
                        shape = RoundedCornerShape(12.dp),
                        value = uiState.email?: "No Email Available",
                        onValueChange = {
                            viewModel.handleEvent(
                                MyAccountEvent.FullNameChanged(
                                    it
                                )
                            )                        },
                        placeholder = {
                            Text(
                                ("E-poçtunuzu daxil edin"),
                                color =  Color(0xFF7D7D7D)
                            )
                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .border(1.dp, Color(0xFFe0e4e8), RoundedCornerShape(12.dp)),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        colors = TextFieldDefaults.colors(
                            disabledTextColor = Color.Gray,
                            disabledContainerColor = Color(0xFFe0e4e8),
//                            focusedIndicatorColor=Color.Red,
//                            unfocusedIndicatorColor=Color.Red
                            disabledIndicatorColor=Color.Transparent
//                            errorIndicatorColor

                            ),
                        enabled = false,

                        )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                AuthButton(
                    text = "Dəyişiklikləri yadda saxla",
                    onClick = {
                        viewModel.handleEvent(MyAccountEvent.Submit)
                        viewModel.updateProfile(
                            UpdateFullNameRequest(
                                uiState.fullName,
                                )
                        )
                        navController.popBackStack()
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
    )
}
