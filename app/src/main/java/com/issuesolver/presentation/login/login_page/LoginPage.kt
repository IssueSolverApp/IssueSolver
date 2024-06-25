package com.issuesolver.presentation.login.login_page

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.issuesolver.R
import com.issuesolver.presentation.common.AuthButton
import com.issuesolver.presentation.common.ErrorText
import kotlinx.coroutines.launch


@SuppressLint("RememberReturnType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(
    navController: NavController, viewModel: LoginPageViewModel = hiltViewModel(),
) {
    var showPassword by remember { mutableStateOf(false) }
    val uiState by viewModel.uiState.collectAsState()
    val isEmailError = uiState.emailError != null
    val isPasswordError = uiState.passwordError != null
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val keyboardHeight = WindowInsets.ime.getBottom(LocalDensity.current)

    LaunchedEffect(key1 = keyboardHeight) {
        coroutineScope.launch {
            scrollState.scrollBy(keyboardHeight.toFloat())
        }
    }
    Scaffold{ padding ->
        Box(
            modifier = Modifier
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
                        "Daxil olun",
                        style = MaterialTheme.typography.headlineMedium,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Start,
                        color = Color.Black
                    )
                    Text(
                        "Zəhmət olmasa, giriş üçün məlumatlarınızı daxil edin.",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        textAlign = TextAlign.Start,
                        color = Color(0xFF9D9D9D),
                        modifier = Modifier
                            .padding(top = 10.dp)
                    )
                }
                Divider(
                    thickness = 0.5.dp,
                    color = Color(0xFF2981FF)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    Modifier.padding(top = 20.dp)
                ) {
                    Text(
                        "E-poçt",
                        color = if (isEmailError) Color.Red else Color.Black,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 15.sp,
                    )
                    TextField(
                        shape = RoundedCornerShape(12.dp),
                        value = uiState.email,
                        onValueChange = { viewModel.handleEvent(LoginPageEvent.EmailChanged(it)) },
                        placeholder = {
                            Text(
                                ("E-poçtunuzu daxil edin"),
                                color = if (isEmailError) Color.Red else Color.Gray
                            )
                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .then(
                                if (isEmailError) Modifier.border(
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
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            errorContainerColor = Color.White,
                            disabledTextColor = Color(0xFF2981FF),
                            focusedIndicatorColor = Color.Transparent,
                            errorCursorColor = Color.Red,
                            cursorColor = Color(0xFF2981FF)
                        ),
                    )
                    ErrorText(
                        errorMessage = uiState.emailError,
//                        isVisible = isEmailError
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        "Şifrə",
                        color = if (isPasswordError) Color.Red else Color.Black,
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 15.sp,
                    )
                    TextField(
                        shape = RoundedCornerShape(12.dp),
                        value = uiState.password,
                        onValueChange = {
                            viewModel.handleEvent(
                                LoginPageEvent.PasswordChanged(
                                    it
                                )
                            )
                        },
                        placeholder = {
                            Text(
                                "Şifrənizi daxil edin",
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
                                else Modifier.border(
                                    1.dp,
                                    Color.White,
                                    RoundedCornerShape(12.dp)
                                )
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
                        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val icon =
                                if (showPassword) painterResource(R.drawable.unhiddeneye) else painterResource(
                                    R.drawable.hiddeneye
                                )
                            val description =
                                if (showPassword) "Hide password" else "Show password"
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
                        errorMessage = uiState.passwordError,
//                        isVisible = isPasswordError
                    )
                    Text(
                        modifier = Modifier
                            .clickable(
                                onClick = { navController.navigate("email verification") },
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            )
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .padding(
                                top = 12.dp,
//                                    bottom=100.dp
                            ),
                        text = "Şifrənizi unutmusunuz?",
                        color = Color(0xFF4D96FF)
                    )
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(Color(0xFFF0F4F9)),
            ) {
                AuthButton(
                    text = "Daxil ol",
                    onClick = { viewModel.handleEvent(LoginPageEvent.Submit) },
                    enabled = uiState.isInputValid,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 27.dp)
                ) {
                    Row {
                        Text(
                            "Hesabınız yoxdur?",
                            color = Color(0xFF9D9D9D)
                        )
                        Text(
                            modifier = Modifier
                                .clickable(
                                    onClick = { },
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ),
                            text = "Qeydiyyatdan keç",

                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }

}