package com.issuesolver.presentation.login.daxil_ol_verification_code

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun VerificationCodePage() {
    var otpValue by remember { mutableStateOf(Array(7) { "" }) }  // OTP array of 6 characters
//    var remainingTime by remember { mutableStateOf(30) }  // 30 seconds countdown
//
//    LaunchedEffect(Unit) {
//        while (remainingTime > 0) {
//            delay(1000)
//            remainingTime--
//        }
//    }


    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Təsdiq Kodu",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    "E-poçtunuza gələn təsdiq kodunu daxil edin.",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Divider(color = Color.Gray, thickness = 0.5.dp)
                Spacer(modifier = Modifier.height(16.dp))


                OtpInputFields(otpValue) { index, value ->
                    otpValue[index] = value
                }



                Spacer(modifier = Modifier.height(16.dp))
                Text("Qalan vaxt: "
//                        + "$remainingTime saniyə"
                    , style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(0.85f),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text("Təsdiqlə")
                }
                Spacer(modifier = Modifier.height(16.dp))
                TextButton(onClick = { /* Logic to resend code */ }) {
                    Text("Kodu yenidən göndər")
                }
            }
        }
    }
}

@Composable
fun OtpInputFields(otp: Array<String>, onValueChange: (Int, String) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        otp.forEachIndexed { index, value ->
            if (index == 3) {
                Text(
                    text = "-",  // Display a dash for the fourth element
                    modifier = Modifier.padding(top = 14.dp),
                    style = MaterialTheme.typography.headlineMedium
                )
            } else {
                OutlinedTextField(
                    value = value,
                    onValueChange = { newValue ->
                        if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                            onValueChange(index, newValue)
                        }
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = if (index == 5) ImeAction.Done else ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { },
                        onDone = { }
                    ),
                    textStyle = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .width(45.dp)
                        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun VerificationCodePagePreview() {
    MaterialTheme {
        VerificationCodePage()
    }
}


















//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Color.Companion.Transparent
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.TextFieldValue
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import kotlinx.coroutines.delay
//
//@Composable
//fun VerificationCodePage() {
//    val otpValue = remember { mutableStateOf(TextFieldValue("")) }
//
//    var code by remember { mutableStateOf("") }
//    var remainingTime by remember { mutableStateOf(30) } // 30 seconds countdown
//
//    LaunchedEffect(Unit) {
//        while (remainingTime > 0) {
//            delay(1000)
//            remainingTime--
//        }
//    }
//
//
//    Scaffold { padding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(padding),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text("Təsdiq Kodu", style = MaterialTheme.typography.headlineMedium)
//            Text("E-poçtunuza gələn təsdiq kodunu daxil edin.", style = MaterialTheme.typography.bodyMedium)
//            Spacer(modifier = Modifier.height(16.dp))
//            Divider(color = Color.Gray
//                , thickness = 1.dp)
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//
//            OtpBoxes(otpValue.value.text)
//            Spacer(modifier = Modifier.height(16.dp))
//            Text("Qalan vaxt:", style = MaterialTheme.typography.bodyMedium)
//            Spacer(modifier = Modifier.height(16.dp))
//
//
//
//            Column(
//                modifier = Modifier
//                    .fillMaxSize(),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Bottom
//            ) {
//                Button(
//                    onClick = {},
//                    modifier = Modifier.fillMaxWidth(0.8f)
//                ) {
//                    Text("Təsdiqlə")
//                }
//                Spacer(modifier = Modifier.height(8.dp))
//                TextButton(onClick = { }) {
//                    Text("Kodu yenidən göndər")
//                }
//
//
//            }
//
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun OtpBoxes(otpValue: String, onOtpChange: (String) -> Unit) {
//    val maxChar = 1
//    val otpArray = remember { Array(6) { "" } }
//
//    // Initialize or update otpArray based on otpValue
//    LaunchedEffect(otpValue) {
//        otpValue.forEachIndexed { index, c ->
//            if (index < otpArray.size) {
//                otpArray[index] = c.toString()
//            }
//        }
//    }
//
//    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
//        otpArray.forEachIndexed { index, value ->
//            var text by remember { mutableStateOf(value) }
//
//            OutlinedTextField(
//                value = text,
//                onValueChange = { newValue ->
//                    if (newValue.length <= maxChar) {
//                        text = newValue
//                        // Update the OTP array and value
//                        otpArray[index] = newValue
//                        onOtpChange(otpArray.joinToString(""))
//                    }
//                },
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                textStyle = TextStyle(
//                    fontSize = 20.sp,
//                    textAlign = TextAlign.Center
//                ),
//                colors = TextFieldDefaults.outlinedTextFieldColors(
//                    unfocusedBorderColor = Color.Transparent,
//                    focusedBorderColor = Color.Transparent
//                ),
//                singleLine = true,
//                modifier = Modifier.width(40.dp)
//            )
//        }
//    }
//}
//
//
//
//@Preview(showBackground = true)
//@Composable
//fun VerificationCodePagePreview() {
//    MaterialTheme {
//        VerificationCodePage()
//    }
//}
