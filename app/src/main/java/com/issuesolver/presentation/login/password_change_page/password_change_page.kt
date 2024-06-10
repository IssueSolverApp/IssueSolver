package com.issuesolver.presentation.login.password_change_page

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordChangePage() {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf(false) }

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
            Text("Yeni şifrə",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start

            )
                Text("Daxil olmaq üçün yeni şifrə təyin edin.", style = MaterialTheme.typography.bodySmall,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Start,

                    )}
            Spacer(modifier = Modifier.height(8.dp))

            Divider(
                color = Color.Gray, thickness = 0.5.dp,

                )
            Spacer(modifier = Modifier.height(8.dp))

            Column() {
                Text(
                    "Şifrə",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 15.sp,
                )
                TextField(
                    shape = RoundedCornerShape(12.dp),
                    value = newPassword,

                    onValueChange = {
                        newPassword = it
                        passwordError = false
                    },

                    placeholder = {Text ("Şifrənizi təyin edin") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "Şifrənin təsdiqi",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 15.sp,
                )
                TextField(
                    shape = RoundedCornerShape(12.dp),
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    placeholder = { Text("Şifrənizi təsdiq edin") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent, // Transparent underline when focused
                        unfocusedIndicatorColor = Color.Transparent // Transparent underline when unfocused
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
            }
                Spacer(modifier = Modifier.height(16.dp))




                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Button(
                        onClick = {},
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Yenilə")
                    }

                }


            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PasswordChangePagePreview() {
    MaterialTheme {
        PasswordChangePage()
    }
}
