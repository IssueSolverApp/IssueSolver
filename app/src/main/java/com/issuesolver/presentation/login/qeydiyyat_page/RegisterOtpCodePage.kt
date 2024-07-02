package com.issuesolver.presentation.login.qeydiyyat_page

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.issuesolver.R
import com.issuesolver.common.AlertDialogExample
import com.issuesolver.common.Resource
import com.issuesolver.common.StatusR
import com.issuesolver.domain.entity.networkModel.RequestOtp
import com.issuesolver.domain.entity.networkModel.ResendOtpModel
import com.issuesolver.presentation.common.AuthButton
import com.issuesolver.presentation.common.ErrorText
import com.issuesolver.presentation.login.daxil_ol_verification_code.OtpInputField
import kotlinx.coroutines.delay


@SuppressLint("DefaultLocale")
@Composable
fun RegisterOtpCodePage(
    navController: NavController,
    email: String?,
    viewModel: ConfirmOtpViewModel = hiltViewModel()
) {

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
    val confirmOtpState by viewModel.confirmOtpState.collectAsState()

    val uiState by viewModel.uiState.collectAsState()
    val isOtpValueError = uiState.otpValueError != null


    when (confirmOtpState.status) {
        StatusR.LOADING -> {
            CircularProgressIndicator()
        }

        StatusR.SUCCESS -> {
            navController.navigate("login")
        }

        StatusR.ERROR -> {
            Log.e("ERRORTAG", confirmOtpState.message.toString())
        }
    }

    val resendOtpState by viewModel.resendOtpState.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(resendOtpState) {
        if (resendOtpState.status == StatusR.ERROR) {
            showDialog = true
        }
    }

    when (resendOtpState.status) {
        StatusR.LOADING -> {
            CircularProgressIndicator()
        }

        StatusR.SUCCESS -> {

        }

        StatusR.ERROR -> {
            //Dialog
            resendOtpState.message?.let {
                if (showDialog) {
                    AlertDialogExample(
                        message = it,
//                        onDismiss = { showDialog = false },
                        onConfirmation = { showDialog = false }
                    )
                }
            }
        }
    }


    Scaffold { padding ->
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
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            "Təsdiq Kodu",
                            style = MaterialTheme.typography.headlineMedium,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Start,
                            color = Color.Black,
                        )
                        Row {
                            Image(
                                painter = painterResource(R.drawable.timer),
                                contentDescription = "timer",
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                            Text(
                                " $formattedTime",
                                fontSize = 18.sp,
                                color = Color(0xFF4D96FF),
                                style = MaterialTheme.typography.headlineMedium,
                            )
                        }
                    }
                    Text(
                        "E-poçtunuza gələn təsdiq kodunu daxil edin.",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 15.sp,
                        textAlign = TextAlign.Start,
                        color = Color(0xFF9D9D9D),
                        modifier = Modifier
                            .padding(top = 10.dp, bottom = 20.dp),
                    )
                    Divider(
                        thickness = 0.5.dp,
                        color = Color(0xFF2981FF)
                    )
                    Column(
                        Modifier.padding(top = 28.dp)
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
                                isOtpValueError = isOtpValueError,
                                onOtpModified = { value, otpFilled ->
                                    otpValue =
                                        TextFieldValue(value, selection = TextRange(value.length))
                                    isOtpFilled = otpFilled
                                    if (otpFilled) {
                                        keyboardController?.hide()
                                    }
                                }
                            )
//                            ErrorText(
//                                errorMessage = uiState.emailError,
//                        isVisible = isEmailError
//                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(150.dp))
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            ) {
                AuthButton(
                    text = "Təsdiqlə",
                    onClick = {
                        viewModel.confirmRegister(RequestOtp(otpCode = otpValue.text))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 26.dp)
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .clickable(
                            onClick = { viewModel.resendOtp(ResendOtpModel(email)) },
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ), text = "Kodu yenidən göndər",
                    fontSize = 15.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}