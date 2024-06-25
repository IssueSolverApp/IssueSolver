package com.issuesolver.presentation.login.email_verification_page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.issuesolver.R
import com.issuesolver.presentation.common.AuthButton
import com.issuesolver.presentation.common.ErrorText
import com.issuesolver.presentation.navigation.NavigationEvent
import com.issuesolver.presentation.navigation.mockNavController
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

@Composable
fun EmailVerificationPage(
    navController: NavController,
    viewModel: EmailVerificationPageViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val isEmailError = uiState.emailError != null



    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 4.dp)
                .padding(20.dp)
                .padding(padding)
                .imePadding()
        ) {
            Column() {
                Column() {
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
                Divider(
                    thickness = 0.5.dp,
                    color = Color(0xFF2981FF)
                )
                Spacer(modifier = Modifier.height(32.dp))
                Column(
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
                                color = if (isEmailError) Color.Red else Color.Gray
                            )
                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .then(
                                if (isEmailError) Modifier.border(
                                    1.dp,
                                    Color.Red,
                                    RoundedCornerShape(12.dp)
                                )
                                else Modifier.border(1.dp, Color.White, RoundedCornerShape(12.dp))
                            ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        colors = TextFieldDefaults.colors(
                            disabledTextColor = Color(0xFF2981FF),
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            disabledContainerColor = Color.White,
                            errorContainerColor = Color.White,
                            cursorColor = Color(0xFF2981FF),
                            errorCursorColor = Color.Red,
                            focusedIndicatorColor = Color.Transparent,
                        )
                        )
                    uiState.emailError?.let {
                        ErrorText(errorMessage = it)
                    }
                }
            }
            Column(
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                AuthButton(
                    text = "Təsdiq kodu göndər",
                    onClick = {
//                        viewModel.handleSubmit {
//                            navController.navigate("otp")
//                        }
                        viewModel.handleSubmit()
                              },
                    enabled = uiState.isInputValid,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
    if (viewModel.navigation) {
        navController.navigate("otp")
    }
}
@Preview(showBackground = true)
@Composable
fun EmailVerificationPagePreview() {
    MaterialTheme {
        EmailVerificationPage(navController = mockNavController())
    }
}
