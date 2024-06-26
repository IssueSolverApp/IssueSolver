package com.issuesolver.presentation.login.qeydiyyat_page

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.issuesolver.R
import com.issuesolver.presentation.common.AuthButton
import com.issuesolver.presentation.login.daxil_ol_verification_code.OtpInputField
import kotlinx.coroutines.delay


@SuppressLint("DefaultLocale")
@Composable
fun RegisterOtpCodePage(navController: NavController) {

    val context = LocalContext.current
    var otpValue by remember { mutableStateOf(TextFieldValue("")) }
    var isOtpFilled by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    (LocalView.current.context as Activity)

    var remainingTime by remember { mutableStateOf(180) }

    LaunchedEffect(Unit) {
        while (remainingTime > 0) {
            delay(1000)
            remainingTime--
        }
    }

    val minutes = remainingTime / 60
    val seconds = remainingTime % 60

    val formattedTime = String.format("%02d:%02d", minutes, seconds)

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
                    Text("Təsdiq Kodu",
                        style = MaterialTheme.typography.headlineMedium,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Start,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(top = 24.dp),


                        )
                    Text("E-poçtunuza gələn təsdiq kodunu daxil edin.",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Start,
                        color = Color(0xFF9D9D9D),
                        modifier = Modifier
                            .padding(top = 10.dp,bottom = 20.dp),

                        )
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(
                        thickness = 0.5.dp,
                        color= Color(0xFF2981FF)

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
                                    otpValue = TextFieldValue(value, selection = TextRange(value.length))
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
                Text("Qalan vaxt: $formattedTime", style = MaterialTheme.typography.bodyMedium,
                    fontSize = 17.sp,
                    color= Color(0xFF2981FF)
                )
            }


            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier.align(Alignment.BottomCenter)
                    .padding(bottom = 27.dp)

//                    .fillMaxSize(),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Bottom
            ) {
                AuthButton(
                    text = "Təsdiqlə",
                    onClick = { navController.navigate("login")},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(

                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally) // Center the text horizontally

                        .clickable (
                            onClick = { },
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,

                            )
                    , text = "Kodu yenidən göndər",
                    fontSize = 15.sp,


                    color = MaterialTheme.colorScheme.primary)
            }



        }
    }
}