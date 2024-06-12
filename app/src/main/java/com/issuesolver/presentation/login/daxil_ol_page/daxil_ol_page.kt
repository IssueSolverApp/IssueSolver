package com.issuesolver.presentation.login.daxil_ol_page

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.issuesolver.presentation.common.AuthButton

@OptIn( ExperimentalMaterial3Api::class)
@Composable
fun LoginPage() {
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }



    Scaffold { padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
            .padding(20.dp)
            .imePadding()
        ) {

            Column() {
                Column(
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
                        color = Color(0xFF9D9D9D)

                    )
                }


                Spacer(modifier = Modifier.height(8.dp))

                Divider(
                    color = Color.Gray, thickness = 0.5.dp,

                )
                Spacer(modifier = Modifier.height(8.dp))





                Column() {
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
                        placeholder = { Text("E-poçtunuzu daxil edin",
                            color = Color(0xFF9D9D9D)) },
                        singleLine = true,
                        modifier =  Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                      colors = TextFieldDefaults.textFieldColors(
                          focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
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
                        placeholder = { Text("Şifrənizi daxil edin",
                            color = Color(0xFF9D9D9D)) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent, // Transparent underline when focused
                            unfocusedIndicatorColor = Color.Transparent // Transparent underline when unfocused
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )

                    TextButton(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                    ) {
                        Text("Şifrənizi unutmusunuz?")
                    }
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
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Hesabınız yoxdur?",
                        color = Color(0xFF9D9D9D)
                          )
                    TextButton(onClick = { }) {
                        Text("Qeydiyyatdan keç", color = MaterialTheme.colorScheme.primary)
                    }
                }


            }
        }

    }
}
@Composable
fun HorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = DividerDefaults.Thickness,
    color: androidx.compose.ui.graphics.Color = DividerDefaults.color
): Unit {
}

@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    MaterialTheme {
        LoginPage()
    }
}
