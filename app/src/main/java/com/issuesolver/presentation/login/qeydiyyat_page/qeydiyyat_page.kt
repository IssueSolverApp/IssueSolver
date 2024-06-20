package com.issuesolver.presentation.login.qeydiyyat_page

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.issuesolver.R
import com.issuesolver.presentation.common.AuthButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPage() {


    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(value = false) }



    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.dp)
                .padding(20.dp)
                .imePadding()

        ) {

            Column(

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
                        color = Color.Black,


                        )

                    Text(
                        "Zəhmət olmasa, giriş üçün məlumatlarınızı daxil edin.",
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
                        "Ad, Soyad",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 15.sp,
                    )
                    TextField(
                        shape = RoundedCornerShape(12.dp),
                        value = email,
                        onValueChange = {
                            email = it
                            emailError = false
                        },
                        isError = emailError,
                        placeholder = {
                            Text(
                                ("Ad, Soyad"),
                                color = Color(0xFF9D9D9D)
                            )
                        },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),

                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White, // Background color of the TextField
                            focusedIndicatorColor = Color.White, // Underline color when focused
                            unfocusedIndicatorColor = Color.White, // Underline color when unfocused
                            disabledTextColor = Color.Gray, // Text color when TextField is disabled
                            errorIndicatorColor = Color.Red, // Underline color when in error state
                            errorCursorColor = Color.Red, // Cursor color when in error state
                            cursorColor = Color.White // Cursor color
                        )

                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Column(
                        Modifier.padding(top = 20.dp)

                    ) {
                        Text(
                            "E-poçt",
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 15.sp,
                        )
                        TextField(
                            shape = RoundedCornerShape(12.dp),
                            value = email,
                            onValueChange = {
                                email = it
                                emailError = false
                            },
                            isError = emailError,
                            placeholder = {
                                Text(
                                    ("E-poçtunuzu daxil edin"),
                                    color = Color(0xFF9D9D9D)
                                )
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),

                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.White, // Background color of the TextField
                                focusedIndicatorColor = Color.White, // Underline color when focused
                                unfocusedIndicatorColor = Color.White, // Underline color when unfocused
                                disabledTextColor = Color.Gray, // Text color when TextField is disabled
                                errorIndicatorColor = Color.Red, // Underline color when in error state
                                errorCursorColor = Color.Red, // Cursor color when in error state
                                cursorColor = Color.White // Cursor color
                            )

                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            "Şifrə",
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 15.sp,
                        )
                        TextField(
                            shape = RoundedCornerShape(12.dp),
                            value = password,
                            onValueChange = { password = it },
                            placeholder = {
                                Text(
                                    "Şifrənizi təyin edin",
                                    color = Color(0xFF9D9D9D)
                                )
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.White, // Background color of the TextField
                                focusedIndicatorColor = Color.White, // Underline color when focused
                                unfocusedIndicatorColor = Color.White, // Underline color when unfocused
                                disabledTextColor = Color.Gray, // Text color when TextField is disabled
                                errorIndicatorColor = Color.Red, // Underline color when in error state
                                errorCursorColor = Color.Red, // Cursor color when in error state
                                cursorColor = Color.White // Cursor color
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
                                        tint = Color(0xFF2981FF),
                                        contentDescription = description
                                    )
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            "Şifrənin təsdiqi ",
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 15.sp,
                        )
                        TextField(
                            shape = RoundedCornerShape(12.dp),
                            value = password,
                            onValueChange = { password = it },
                            placeholder = {
                                Text(
                                    "Şifrəni təsdiq edin",
                                    color = Color(0xFF9D9D9D)
                                )
                            },
                            singleLine = true,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                containerColor = Color.White, // Background color of the TextField
                                focusedIndicatorColor = Color.White, // Underline color when focused
                                unfocusedIndicatorColor = Color.White, // Underline color when unfocused
                                disabledTextColor = Color.Gray, // Text color when TextField is disabled
                                errorIndicatorColor = Color.Red, // Underline color when in error state
                                errorCursorColor = Color.Red, // Cursor color when in error state
                                cursorColor = Color.White // Cursor color
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
                                        tint = Color(0xFF2981FF),
                                        contentDescription = description
                                    )
                                }
                            }
                        )

                        Text(
                            modifier = Modifier
                                .clickable {}
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.End)
                                .padding(top = 10.dp), text = "Şifrənizi unutmusunuz?",

                            color = MaterialTheme.colorScheme.primary
                        )


                    }


                }

                Spacer(modifier = Modifier.height(16.dp))


                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    AuthButton(
                        text = "Daxil ol",
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row {
                            Text(
                                "Hesabınız yoxdur?",
                                color = Color(0xFF9D9D9D)
                            )
                            Text(
                                modifier = Modifier.clickable {

                                }, text = "Qeydiyyatdan keç",

                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }


                }
            }

        }

    }
}
