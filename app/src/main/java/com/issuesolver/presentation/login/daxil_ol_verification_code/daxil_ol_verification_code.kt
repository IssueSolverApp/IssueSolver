package com.issuesolver.presentation.login.daxil_ol_verification_code





import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VerificationCodePage() {
    val context = LocalContext.current
    var otpValue by remember { mutableStateOf(TextFieldValue("")) }
    var isOtpFilled by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    (LocalView.current.context as Activity).window.statusBarColor = Color.White.toArgb()

    var remainingTime by remember { mutableStateOf(30) } // 30 seconds countdown

    LaunchedEffect(Unit) {
        while (remainingTime > 0) {
            delay(1000)
            remainingTime--
        }
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Təsdiq Kodu", style = MaterialTheme.typography.headlineMedium)
            Text("E-poçtunuza gələn təsdiq kodunu daxil edin.", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(24.dp),
                color = Color.White
            ) {
                OtpInputField(
                    modifier = Modifier
                        .padding(top = 48.dp)
                        .focusRequester(focusRequester),
                    otpText = otpValue.text,
                    shouldCursorBlink = false,
                    onOtpModified = { value, otpFilled ->
                        otpValue = TextFieldValue(value, selection = TextRange(value.length))
                        isOtpFilled = otpFilled
                        if (otpFilled) {
                            keyboardController?.hide()
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text("Qalan vaxt: $remainingTime", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(0.8f)
                ) {
                    Text("Təsdiqlə")
                }
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(onClick = { /* TODO: Add resend code logic */ }) {
                    Text("Kodu yenidən göndər")
                }
            }
        }
    }
}

@Composable
fun OtpInputField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpLength: Int = 6, // Total length of 6 for OTP
    shouldShowCursor: Boolean = false,
    shouldCursorBlink: Boolean = false,
    onOtpModified: (String, Boolean) -> Unit
) {
    // Assume OTP length is 6 for simplicity
    val text = remember { mutableStateOf(otpText) }

    BasicTextField(
        value = text.value,
        onValueChange = {
            // Only accept changes if they are numbers and the total length doesn't exceed otpLength
            if (it.length <= otpLength && it.all { char -> char.isDigit() }) {
                text.value = it
                onOtpModified(it, it.length == otpLength)
            }
        },
        modifier = modifier,
        textStyle = TextStyle(textAlign = TextAlign.Center, fontSize = 24.sp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (i in 0 until 3) {
                    CharacterBox(character = if (i < text.value.length) text.value[i].toString() else "", isFocused = i == text.value.length)
                    Spacer(modifier = Modifier.width(4.dp))
                }
                Text("-", style = TextStyle(color = Color.Gray, fontSize = 24.sp, textAlign = TextAlign.Center))
                Spacer(modifier = Modifier.width(4.dp))
                for (i in 3 until 6) {
                    CharacterBox(character = if (i < text.value.length) text.value[i].toString() else "", isFocused = i == text.value.length)
                    if (i < 5) Spacer(modifier = Modifier.width(4.dp))
                }
            }
        }
    )
}

@Composable
fun CharacterBox(character: String, isFocused: Boolean) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(36.dp)
            .border(1.dp, if (isFocused) Color.Black else Color.Gray, RoundedCornerShape(6.dp))
    ) {
        Text(text = character, style = TextStyle(fontSize = 24.sp, textAlign = TextAlign.Center))
    }
}

@Composable
internal fun CharacterContainer(
    index: Int,
    text: String,
    shouldShowCursor: Boolean,
    shouldCursorBlink: Boolean,
) {
    val isFocused = text.length == index
    val character = when {
        index < text.length -> text[index].toString()
        else -> ""
    }

    // Cursor visibility state
    val cursorVisible = remember { mutableStateOf(shouldShowCursor) }

    // Blinking effect for the cursor
    LaunchedEffect(key1 = isFocused) {
        if (isFocused && shouldShowCursor && shouldCursorBlink) {
            while (true) {
                delay(800) // Adjust the blinking speed here
                cursorVisible.value = !cursorVisible.value
            }
        }
    }

    Box(contentAlignment = Alignment.Center) {
        Text(
            modifier = Modifier
                .width(36.dp)
                .border(
                    width = when {
                        isFocused -> 2.dp
                        else -> 1.dp
                    },
                    color = Color.Gray,  // Add a color for the border here
                    shape = RoundedCornerShape(6.dp)
                )

                .padding(2.dp),
            text = character,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )

        // Display cursor when focused
        AnimatedVisibility(visible = isFocused && cursorVisible.value) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(2.dp)
                    .height(24.dp) // Adjust height according to your design
            )
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


