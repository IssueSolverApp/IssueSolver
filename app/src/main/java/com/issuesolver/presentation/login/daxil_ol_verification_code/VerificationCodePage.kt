package com.issuesolver.presentation.login.daxil_ol_verification_code


import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.issuesolver.R
import com.issuesolver.domain.entity.networkModel.RequestOtp
import com.issuesolver.presentation.common.AuthButton
import com.issuesolver.presentation.navigation.mockNavController
import kotlinx.coroutines.delay

@SuppressLint("DefaultLocale")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VerificationCodePage(
    navController: NavController,
    viewModel: VerificationCodePageViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var otpValue by remember { mutableStateOf(TextFieldValue("")) }
    var isOtpFilled by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    //var otpValue by remember { mutableStateOf(TextFieldValue("")) }


    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    (LocalView.current.context as Activity)

    var remainingTime by remember { mutableLongStateOf(180) }

    LaunchedEffect(Unit) {
        while (remainingTime > 0) {
            delay(1000)
            remainingTime--
        }
    }

    val minutes = remainingTime / 60
    val seconds = remainingTime % 60

    val formattedTime = String.format("%02d:%02d", minutes, seconds)

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
                    Box(
                        modifier = Modifier

                            .padding(top = 20.dp)
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
                        "Təsdiq Kodu",
                        style = MaterialTheme.typography.headlineMedium,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Start,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(top = 24.dp),


                        )
                    Text(
                        "E-poçtunuza gələn təsdiq kodunu daxil edin.",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Start,
                        color = Color(0xFF9D9D9D),
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 20.dp),

                        )
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(
                        thickness = 0.5.dp,
                        color = Color(0xFF2981FF)

                    )
                    Spacer(modifier = Modifier.height(8.dp))





                    Column(
                        Modifier.padding(top = 20.dp)

                    ) {
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth(),
                            color = Color.Transparent,
                        ) {
                            OtpInputField(
                                modifier = Modifier
                                    .focusRequester(focusRequester),
                                otpText = otpValue.text,
                                shouldCursorBlink = false,
                                onOtpModified = { value, otpFilled ->
                                    otpValue =
                                        TextFieldValue(value, selection = TextRange(value.length))
                                    isOtpFilled = otpFilled
                                    if (otpFilled) {
                                        keyboardController?.hide()
                                    }
                                }
                            )
                        }
                    }
                }


                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    "Qalan vaxt: $formattedTime", style = MaterialTheme.typography.bodyMedium,
                    fontSize = 17.sp,
                    color = Color(0xFF2981FF)
                )
            }


            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 27.dp)

//                    .fillMaxSize(),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Bottom
            ) {
                AuthButton(
                    text = "Təsdiqlə",
                    onClick = {
                        viewModel.otpTrust(
                            RequestOtp(
                                otpCode = otpValue.text
                            )
                        )
                        navController.navigate("password change")
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(

                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally) // Center the text horizontally

                        .clickable(
                            onClick = { },
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,

                            ), text = "Kodu yenidən göndər",
                    fontSize = 15.sp,


                    color = MaterialTheme.colorScheme.primary
                )
            }


        }
    }
    )
}


@Composable
fun OtpInputField(
    modifier: Modifier = Modifier,
    otpText: String,
    otpLength: Int = 6,
    shouldShowCursor: Boolean = false,
    shouldCursorBlink: Boolean = false,
    onOtpModified: (String, Boolean) -> Unit
) {
    val text = remember { mutableStateOf(otpText) }

    BasicTextField(
        value = text.value,
        onValueChange = {
            if (it.length <= otpLength && it.all { char -> char.isDigit() }) {
                text.value = it
                onOtpModified(it, it.length == otpLength)
            }
        },
        modifier = modifier,
        textStyle = TextStyle(textAlign = TextAlign.Center, fontSize = 17.sp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                for (i in 0 until otpLength) {
                    CharacterBox(
                        character = text.value.getOrNull(i)?.toString() ?: "",
                        isFocused = i == text.value.length,
                        modifier = Modifier
                            .weight(1f, fill = true)
                            .padding(horizontal = 4.dp)
                    )
                    if (i == 2) {
                        Text(
                            "-",
                            style = TextStyle(
                                color = Color(0xFF2981FF),
                                fontSize = 24.sp,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun CharacterBox(character: String, isFocused: Boolean, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .defaultMinSize(minWidth = 52.dp, minHeight = 65.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .border(
                1.dp,
                if (isFocused) Color(0xFF2981FF) else Color.White,
                RoundedCornerShape(12.dp)
            )
    ) {
        Text(
            text = character,
            style = TextStyle(fontSize = 17.sp, textAlign = TextAlign.Center)
        )
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

    val cursorVisible = remember { mutableStateOf(shouldShowCursor) }

    LaunchedEffect(key1 = isFocused) {
        if (isFocused && shouldShowCursor && shouldCursorBlink) {
            while (true) {
                delay(800)
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
                    color = Color.Transparent,
                    shape = RoundedCornerShape(6.dp)
                )

                .padding(2.dp),
            text = character,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )

        AnimatedVisibility(visible = isFocused && cursorVisible.value) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .width(2.dp)
                    .height(24.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun VerificationCodePagePreview() {
    MaterialTheme {
        VerificationCodePage(navController = mockNavController())
    }
}


