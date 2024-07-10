package com.issuesolver.presentation.login.daxil_ol_page_email

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.issuesolver.R
import com.issuesolver.common.StatusR
import com.issuesolver.domain.entity.networkModel.login.ResendOtpModel
import com.issuesolver.presentation.common.AuthButton
import com.issuesolver.presentation.common.ErrorText
import com.issuesolver.presentation.common.LoadingOverlay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailVerificationPage(
    navController: NavController,
    viewModel: EmailVerificationPageViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()
    val isEmailError = uiState.emailError != null

    val forgetPasswordState by viewModel.forgetPasswordState.collectAsState()

    when (forgetPasswordState?.status) {
        StatusR.LOADING -> {
            LoadingOverlay()        }
        StatusR.SUCCESS -> {
            navController.navigate("otp")
            viewModel.clearForgetPasswordState()
        }
        StatusR.ERROR -> {

        }
        else -> {

        }
    }

    Scaffold(content = { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .imePadding()
                .padding(top = 24.dp, start = 20.dp, end = 20.dp, bottom = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
            ) {
                Column() {
                    Box(
                        modifier = Modifier
//                            .padding(top = 20.dp)
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
                    Text(
                        "E-poçtunuzu daxil edin",
                        style = MaterialTheme.typography.headlineMedium,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Start,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(top = 24.dp),
                    )
                    Text(
                        "E-poçt hesabınıza təsdiq kod göndəriləcək.",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Start,
                        color = Color(0xFF9D9D9D),
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 20.dp),
                    )
                }
//                Spacer(
//                    modifier = Modifier.height(8.dp)
//                )
                Divider(
                    thickness = 0.5.dp,
                    color = Color(0xFF2981FF)
                )
//                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    Modifier.padding(top = 32.dp)
                        .verticalScroll(rememberScrollState())

                ) {
                    Text(
                        "E-poçt",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 15.sp,
                    )
                    TextField(
                        shape = RoundedCornerShape(12.dp),
                        value = uiState.email,
                        onValueChange = {
                            viewModel.handleEvent(
                                VerificationCodePageEvent.EmailChanged(
                                    it
                                )
                            )
                        },
                        placeholder = {
                            Text(
                                ("E-poçtunuzu daxil edin"),
                                color = Color.Gray
                            )
                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .border(1.dp, Color.White, RoundedCornerShape(12.dp)),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        colors = TextFieldDefaults.colors(
                            disabledTextColor = Color(0xFF2981FF),
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            disabledContainerColor = Color.White,
                            errorContainerColor = Color.White,
                            cursorColor = Color(0xFF2981FF),
                            focusedIndicatorColor = Color.Transparent,
                        ),
                    )
                    ErrorText(
                        errorMessage = uiState.emailError,
//                        isVisible = isEmailError
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                AuthButton(
                    text = "Təsdiq kodu göndər",
                    onClick = {
                        viewModel.forgetPassword(ResendOtpModel(uiState.email))
                    },
                    enabled = uiState.isInputValid,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
    )
}

