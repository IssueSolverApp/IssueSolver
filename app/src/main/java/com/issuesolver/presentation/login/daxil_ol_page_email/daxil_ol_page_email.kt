package com.issuesolver.presentation.login.daxil_ol_page_email

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.navigation.NavController
import com.issuesolver.R
import com.issuesolver.presentation.common.AuthButton
import com.issuesolver.presentation.navigation.mockNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailVerificationPage(navController: NavController) {

    var email by remember { mutableStateOf("") }

    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.dp)
                .padding(20.dp)
                .imePadding()
        ) {

            Column() {
                Column() {

                    Box(
                        modifier = Modifier

                            .padding(top = 20.dp)
                            .size(40.dp)
                            .clip(RoundedCornerShape(100.dp)) // Apply rounded corners to the background
                            .background(Color.White) // Set the background color
                            .clickable {
                                navController.popBackStack()
                            },
                                contentAlignment = Alignment.Center // Center the content inside the Box

                    ) {
                        Image(
                            painter = painterResource(R.drawable.backarray),
                            contentDescription = "Back",
                            modifier = Modifier.size(24.dp) // Ensures the image fills the Box
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
                Spacer(modifier = Modifier.height(8.dp)
                    )

                Divider(
                    thickness = 0.5.dp,
                    color=Color(0xFF2981FF)

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
                            var emailError = false
                        },

                        placeholder = { Text(
                            ("E-poçtunuzu daxil edin"),
                            color = Color(0xFF9D9D9D)
                        )},
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
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
                    text = "Təsdiq kodu göndər",
                    onClick = {navController.navigate("otp")},
                    modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun EmailVerificationPagePreview() {
    MaterialTheme {
        EmailVerificationPage(navController = mockNavController())
    }
}
