package com.issuesolver.presentation.profile.new_password

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import com.issuesolver.domain.entity.networkModel.ResendOtpModel
import com.issuesolver.presentation.bottombar.AnimatedNavigationBar
import com.issuesolver.presentation.common.AuthButton
import com.issuesolver.presentation.common.ErrorText
import com.issuesolver.presentation.login.daxil_ol_page_email.VerificationCodePageEvent
import com.issuesolver.presentation.navigation.mockNavController
import com.issuesolver.presentation.profile.profile.ProfileScreen

@Composable
fun NewPasswordScreen(
    navController: NavController,
//    viewModel:  = hiltViewModel()

){
    var showPassword1 by remember { mutableStateOf(value = false) }
    var showPassword2 by remember { mutableStateOf(value = false) }
    var showPassword3 by remember { mutableStateOf(value = false) }

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
            .padding(top = 13.dp, start = 20.dp, end = 20.dp, bottom = 34.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
        ) {
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
                    "Yeni şifrə",
                    style = MaterialTheme.typography.headlineMedium,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start,
                    color = Color(0xFF2981FF),
                    modifier = Modifier.padding(top = 24.dp),
                )
                Text(
                    "Yeni şifrə təyin edə bilərsiz.",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Start,
                    color = Color(0xFF9D9D9D),
                    modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
                )
            }
//                Spacer(
//                    modifier = Modifier.height(8.dp)
//                )
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
                    "Cari şifrə",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 15.sp,
                )
                val email2=""
                TextField(
                    shape = RoundedCornerShape(12.dp),
                    value = email2,
                    onValueChange = {},
                    placeholder = {
                        Text("Şifrənizi daxil edin",
                            color=Color(0xFF9D9D9D))
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .border(1.dp, Color.White, RoundedCornerShape(12.dp))
                        .then(
                            if (
//                            isPasswordError
                                false) Modifier.border(
                                1.dp,
                                Color.Red,
                                RoundedCornerShape(12.dp)
                            )
                            else Modifier.border(1.dp, Color.White, RoundedCornerShape(12.dp))
                        ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = TextFieldDefaults.colors(
                        disabledTextColor = Color(0xFF2981FF),
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        errorContainerColor = Color.White,
                        cursorColor = Color(0xFF2981FF),
                        errorCursorColor = Color.Red,
                        focusedIndicatorColor = Color.Transparent,
                    ),
                    visualTransformation = if (showPassword1) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val icon =
                            if (showPassword1) painterResource(R.drawable.unhiddeneye) else painterResource(
                                R.drawable.hiddeneye
                            )
                        val description = if (showPassword1) "Hide password" else "Show password"
                        IconButton(onClick = { showPassword1 = !showPassword1 }) {
                            Icon(
                                painter = icon,
                                tint = if ( false
//                                isPasswordError
                                ) Color.Red else Color(0xFF2981FF),
                                contentDescription = description
                            )
                        }
                    }
                )
                Text(
                    "Yeni şifrə",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top=20.dp)
                )
                val email3=""
                TextField(
                    shape = RoundedCornerShape(12.dp),
                    value = email3,
                    onValueChange = {},
                    placeholder = {
                        Text("Yeni şifrəni təyin edin",
                                color=Color(0xFF9D9D9D))
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .border(1.dp, Color.White, RoundedCornerShape(12.dp))
                        .then(
                            if (
//                            isPasswordError
                                false) Modifier.border(
                                1.dp,
                                Color.Red,
                                RoundedCornerShape(12.dp)
                            )
                            else Modifier.border(1.dp, Color.White, RoundedCornerShape(12.dp))
                        ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = TextFieldDefaults.colors(
                        disabledTextColor = Color(0xFF2981FF),
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        errorContainerColor = Color.White,
                        cursorColor = Color(0xFF2981FF),
                        errorCursorColor = Color.Red,
                        focusedIndicatorColor = Color.Transparent,
                    ),
                    visualTransformation = if (showPassword2) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val icon =
                            if (showPassword2) painterResource(R.drawable.unhiddeneye) else painterResource(
                                R.drawable.hiddeneye
                            )
                        val description = if (showPassword2) "Hide password" else "Show password"
                        IconButton(onClick = { showPassword2 = !showPassword2 }) {
                            Icon(
                                painter = icon,
                                tint = if ( false
//                                isPasswordError
                                ) Color.Red else Color(0xFF2981FF),
                                contentDescription = description
                            )
                        }
                    }
                )
            }
            Text(
                "Şifrənin təsdiqi",
                style = MaterialTheme.typography.bodySmall,
                fontSize = 15.sp,
                modifier = Modifier.padding(top=20.dp)

            )
            val email=""
            TextField(
                shape = RoundedCornerShape(12.dp),
                value = email,
                onValueChange = {},
                placeholder = {
                    Text("Şifrəni təsdiq edin",
                        color=Color(0xFF9D9D9D))
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .border(1.dp, Color.White, RoundedCornerShape(12.dp))
                    .then(
                        if (
//                            isPasswordError
                            false) Modifier.border(
                            1.dp,
                            Color.Red,
                            RoundedCornerShape(12.dp)
                        )
                        else Modifier.border(1.dp, Color.White, RoundedCornerShape(12.dp))
                    ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.colors(
                    disabledTextColor = Color(0xFF2981FF),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    errorContainerColor = Color.White,
                    cursorColor = Color(0xFF2981FF),
                    errorCursorColor = Color.Red,
                    focusedIndicatorColor = Color.Transparent,
                ),
                visualTransformation = if (showPassword3) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon =
                        if (showPassword3) painterResource(R.drawable.unhiddeneye) else painterResource(
                            R.drawable.hiddeneye
                        )
                    val description = if (showPassword3) "Hide password" else "Show password"
                    IconButton(onClick = { showPassword3 = !showPassword3 }) {
                        Icon(
                            painter = icon,
                            tint = if ( false
//                                isPasswordError
                                ) Color.Red else Color(0xFF2981FF),
                            contentDescription = description
                        )
                    }
                }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            AuthButton(
                text = "Yenilə",
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
)
}

@Preview(showBackground = true)
@Composable
fun PreviewNewPasswordScreen() {
    NewPasswordScreen(mockNavController())
}