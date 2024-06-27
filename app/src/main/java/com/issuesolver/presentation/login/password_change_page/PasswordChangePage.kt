package com.issuesolver.presentation.login.password_change_page

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.issuesolver.R
import com.issuesolver.domain.entity.networkModel.ResetPasswordModel
import com.issuesolver.presentation.common.AuthButton
import com.issuesolver.presentation.common.ErrorText
import com.issuesolver.presentation.login.daxil_ol_page.LoginPageEvent
import com.issuesolver.presentation.navigation.mockNavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordChangePage(navController: NavController,viewModel: PasswordChangePageViewModel = hiltViewModel()) {

    var showPassword by remember { mutableStateOf(value = false) }
    var showPassword1 by remember { mutableStateOf(value = false) }
    val uiState by viewModel.uiState.collectAsState()

    val isPasswordError = uiState.newpasswordError != null
    val isRepeatedPasswordError = uiState.repeatedPasswordError != null


    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val keyboardHeight = WindowInsets.ime.getBottom(LocalDensity.current)

    LaunchedEffect(key1 = keyboardHeight) {
        coroutineScope.launch {
            scrollState.scrollBy(keyboardHeight.toFloat())
        }
    }






    Scaffold { padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
            .padding(20.dp)
            .imePadding()
        ) {

            Column(
                Modifier.verticalScroll(scrollState)


            ) {
                Column(
                    Modifier.padding(bottom = 20.dp)

                ) {
                    Text(
                        "Yeni şifrə",
                        style = MaterialTheme.typography.headlineMedium,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Start,
                        color = Color.Black,

                        )
                    Text(
                        "Daxil olmaq üçün yeni şifrə təyin edin.",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Start,
                        color = Color(0xFF9D9D9D),
                        modifier = Modifier
                            .padding(top = 10.dp),


                        )
                }
                Spacer(modifier = Modifier.height(8.dp))

                Divider(
                    thickness = 0.5.dp,
                    color = Color(0xFF2981FF)

                )
                Spacer(modifier = Modifier.height(8.dp))

                Column(
                    Modifier.padding(top = 20.dp)

                ) {
                    Text(
                        "Şifrə",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 15.sp,
                    )
                    TextField(
                        shape = RoundedCornerShape(12.dp),
                        value = uiState.newpassword,
                        onValueChange = {
                            viewModel.handleEvent(
                                PasswordChangePageEvent.PasswordChanged(
                                    it
                                )
                            )
                        },
                        placeholder = {
                            Text(
                                "Şifrənizi təyin edin",
                                color = if (isPasswordError) Color.Red else Color.Gray
                            )
                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .then(
                                if (isPasswordError) Modifier.border(
                                    1.dp,
                                    Color.Red,
                                    RoundedCornerShape(12.dp)
                                )
                                else Modifier.border(1.dp, Color.White, RoundedCornerShape(12.dp))
                            ),

                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            errorContainerColor = Color.White,
                            disabledTextColor = Color(0xFF2981FF),
                            focusedIndicatorColor = Color.Transparent,
                            errorCursorColor = Color.Red,
                            cursorColor = Color(0xFF2981FF)
                        ),
                        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val icon =
                                if (showPassword) painterResource(R.drawable.unhiddeneye) else painterResource(
                                    R.drawable.hiddeneye
                                )
                            val description = if (showPassword) "Hide password" else "Show password"
                            IconButton(onClick = { showPassword = !showPassword }) {
                                Icon(
                                    painter = icon,
                                    tint = if (isPasswordError) Color.Red else Color(0xFF2981FF),
                                    contentDescription = description
                                )
                            }
                        }
                    )

                    ErrorText(
                        errorMessage = uiState.newpasswordError,
//                        isVisible = isPasswordError
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        "Şifrənin təsdiqi",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 15.sp,
                    )
                    TextField(
                        shape = RoundedCornerShape(12.dp),
                        value = uiState.repeatedPassword,
                        onValueChange = {
                            viewModel.handleEvent(
                                PasswordChangePageEvent.RepeatedPasswordChanged(
                                    it
                                )
                            )
                        },
                        placeholder = {
                            Text(
                                "Şifrənizi təsdiq edin",
                                color = if (isRepeatedPasswordError) Color.Red else Color.Gray

                            )
                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .then(
                                if (isRepeatedPasswordError) Modifier.border(
                                    1.dp,
                                    Color.Red,
                                    RoundedCornerShape(12.dp)
                                )
                                else Modifier.border(1.dp, Color.White, RoundedCornerShape(12.dp))
                            ),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            errorContainerColor = Color.White,
                            disabledTextColor = Color(0xFF2981FF),
                            focusedIndicatorColor = Color.Transparent,
                            errorCursorColor = Color.Red,
                            cursorColor = Color(0xFF2981FF)
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = if (showPassword1) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val icon =
                                if (showPassword1) painterResource(R.drawable.unhiddeneye) else painterResource(
                                    R.drawable.hiddeneye
                                )
                            val description =
                                if (showPassword1) "Hide password" else "Show password"
                            IconButton(onClick = { showPassword1 = !showPassword1 }) {
                                Icon(
                                    painter = icon,
                                    tint = if (isRepeatedPasswordError) Color.Red else Color(
                                        0xFF2981FF
                                    ),
                                    contentDescription = description
                                )
                            }
                        }
                    )

                    ErrorText(
                        errorMessage = uiState.repeatedPasswordError,
//                        isVisible = isPasswordError
                    )
                }
//                Spacer(modifier = Modifier.height(16.dp))

                Spacer(modifier = Modifier.height(100.dp))



            }

                Column(
                    modifier = Modifier.align(Alignment.BottomCenter)
//                        .fillMaxSize(),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Bottom
                ) {
                    AuthButton(
                        text = "Yenilə",
                        onClick = {
                            viewModel.handleEvent(PasswordChangePageEvent.Submit)
                            viewModel.resetPassword(ResetPasswordModel(
                                uiState.newpassword,
                                uiState.repeatedPassword
                            ))
                                  },
                        enabled = uiState.isInputValid,

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )

                }


            }
        }

    }


@Preview(showBackground = true)
@Composable
fun PasswordChangePagePreview() {
    MaterialTheme {
        PasswordChangePage(navController = mockNavController())
    }
}
