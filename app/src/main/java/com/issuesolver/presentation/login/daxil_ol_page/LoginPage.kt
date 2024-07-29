package com.issuesolver.presentation.login.daxil_ol_page

import BottomBarScreen
import android.annotation.SuppressLint
import android.widget.Toast
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
import com.issuesolver.common.StatusR
import com.issuesolver.domain.entity.networkModel.login.LoginRequest
import com.issuesolver.presentation.common.AuthButton
import com.issuesolver.presentation.common.ErrorText
import com.issuesolver.presentation.common.LoadingOverlay
import com.issuesolver.presentation.navigation.AuthScreen
import com.issuesolver.presentation.navigation.Graph
import kotlinx.coroutines.launch


@SuppressLint("RememberReturnType")
@Composable
fun LoginPage(
    navController: NavController, viewModel: LoginPageViewModel = hiltViewModel(),
) {


    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    val uiState by viewModel.uiState.collectAsState()
    val isEmailError = uiState.emailError != null
    val isPasswordError = uiState.passwordError != null

    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val keyboardHeight = WindowInsets.ime.getBottom(LocalDensity.current)

    val loginState by viewModel.signInState.collectAsState()

    when(loginState?.status){

        StatusR.LOADING -> {

            LoadingOverlay()

        }

        StatusR.ERROR -> {


        }
        StatusR.SUCCESS -> {
            navController.navigate(Graph.MAIN_SCREEN_PAGE){
                popUpTo(AuthScreen.Login.route) { inclusive = true }
            }
//            Toast.makeText(LocalView.current.context, "Login Success", Toast.LENGTH_SHORT).show()
//            viewModel.clearLoginState()
        }
        else-> {

        }


    }


    LaunchedEffect(key1 = keyboardHeight) {
        coroutineScope.launch {
            scrollState.scrollBy(keyboardHeight.toFloat())
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
                Column(
                    Modifier.padding(bottom = 20.dp)
                ) {
                    Text(
                        "Daxil olun",
                        style = MaterialTheme.typography.headlineMedium,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Start,
                        color = Color(0xFF2981FF)
                    )
                    Text(
                        "Zəhmət olmasa, giriş üçün məlumatlarınızı daxil edin.",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 15.sp,
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
                Column(
                    Modifier.padding(top = 28.dp)
                        .verticalScroll(rememberScrollState())

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
                            .padding(top = 8.dp)
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
                                onClick = { navController.navigate(AuthScreen.EmailVerification.route) },
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            )
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .padding(
                                top = 12.dp,
                            ),
                        text = "Şifrənizi unutmusunuz?",
                        fontSize = 15.sp,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF4D96FF)
                    )
                    Spacer(modifier = Modifier.height(150.dp))
                }
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(Color(0xFFF0F4F9)),
            ) {
                AuthButton(
                    text = "Daxil ol",
                    onClick = {
                        viewModel.handleEvent(LoginPageEvent.Submit)
                        viewModel.signIn(
                            LoginRequest(
                                email = uiState.email,
                                password = uiState.password
                            )
                        )
                    },
                    enabled = uiState.isInputValid,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row {
                        Text(
                            "Hesabınız yoxdur?",
                            fontSize = 15.sp,
                            color = Color(0xFF9D9D9D)
                        )
                        Text(
                            modifier = Modifier
                                .clickable(
                                    onClick = { navController.navigate(AuthScreen.Register.route) },
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ),
                            text = "Qeydiyyatdan keç",
                            fontSize = 15.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
    )
}


