package com.issuesolver.presentation.login.daxil_ol_verification_code

//import android.app.Activity
//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.foundation.background
//import androidx.compose.foundation.border
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.BasicTextField
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.drawWithContent
//import androidx.compose.ui.focus.FocusRequester
//import androidx.compose.ui.focus.focusRequester
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.CompositingStrategy
//import androidx.compose.ui.graphics.graphicsLayer
//import androidx.compose.ui.graphics.toArgb
//import androidx.compose.ui.platform.LocalSoftwareKeyboardController
//import androidx.compose.ui.platform.LocalView
//import androidx.compose.ui.text.TextRange
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.TextFieldValue
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import kotlinx.coroutines.delay
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun VerificationCodePage() {
//    var otpValue by remember { mutableStateOf("") }
//    var isOtpFilled by remember { mutableStateOf(false) }
//    val focusRequester = remember { FocusRequester() }
//    val keyboardController = LocalSoftwareKeyboardController.current
//
//
//
//
//    LaunchedEffect(Unit) {
//        focusRequester.requestFocus()
//        keyboardController?.show()
//    }
//
//
//    (LocalView.current.context as Activity).window.statusBarColor = Color.White.toArgb()
//
//    /**
//     * OTP Screen UI starts here
//     */
//    Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                modifier = Modifier
//                    .graphicsLayer { compositingStrategy = CompositingStrategy.Offscreen }
//                    .drawWithContent {
//                        drawContent()
//                    },
//
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color.White,
//                    titleContentColor = Color.DarkGray,
//                    actionIconContentColor = Color.DarkGray
//                ),
//                title = { Text(text = "Enter One Time Password") },
//                windowInsets = WindowInsets.systemBars.only(WindowInsetsSides.Horizontal)
//            )
//        },
//        bottomBar = {
//            Button(
//                onClick = {},
//                enabled = isOtpFilled,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(24.dp),
//            ) {
//                Text(text = "Continue")
//            }
//        }
//    ) { innerPadding ->
//        Surface(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.White)
//                .padding(24.dp),
//            color = Color.White
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(innerPadding)
//            ) {
//                Text(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(24.dp, 0.dp),
//                    text = "Please verify your phone number with the OTP we sent to (***)***-2193.",
//                    style = MaterialTheme.typography.bodyMedium,
//                    color = Color.DarkGray,
//                    textAlign = TextAlign.Center
//                )
//                Surface(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(Color.White)
//                        .padding(24.dp),
//                    color = Color.White
//                ) {
//                    OtpInputField(
//                        modifier = Modifier
//                            .padding(top = 48.dp)
//                            .focusRequester(focusRequester),
//                        otpText = otpValue,
//                        shouldCursorBlink = false,
//                        onOtpModified = { value, otpFilled ->
//                            otpValue = value
//                            isOtpFilled = otpFilled
//                            if (otpFilled) {
//                                keyboardController?.hide()
//                            }
//                        }
//                    )
//                }
//            }
//        }
//    }
//
//}


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








//@SuppressLint("RememberReturnType")
//@RequiresApi(Build.VERSION_CODES.TIRAMISU)
//@Composable
//fun OtpReceiverEffect(
//    context: Context,
//    onOtpReceived: (String) -> Unit
//) {
//    val otpReceiver = remember { OTPReceiver() }
//
//    /**
//     * This function should not be used to listen for Lifecycle.Event.ON_DESTROY because Compose
//     * stops recomposing after receiving a Lifecycle.Event.ON_STOP and will never be aware of an
//     * ON_DESTROY to launch onEvent.
//     *
//     * This function should also not be used to launch tasks in response to callback events by way
//     * of storing callback data as a Lifecycle.State in a MutableState. Instead, see currentStateAsState
//     * to obtain a State that may be used to launch jobs in response to state changes.
//     */
//    LifecycleResumeEffect {
//        // add ON_RESUME effect here
//        Log.e("OTPReceiverEffect", "SMS retrieval has been started.")
//        startSMSRetrieverClient(context)
//        otpReceiver.init(object : OTPReceiver.OTPReceiveListener {
//            override fun onOTPReceived(otp: String?) {
//                Log.e("OTPReceiverEffect ", "OTP Received: $otp")
//                otp?.let { onOtpReceived(it) }
//                try {
//                    Log.e("OTPReceiverEffect ", "Unregistering receiver")
//                    context.unregisterReceiver(otpReceiver)
//                } catch (e: IllegalArgumentException) {
//                    Log.e("OTPReceiverEffect ", "Error in registering receiver: ${e.message}}")
//                }
//            }
//
//            override fun onOTPTimeOut() {
//                Log.e("OTPReceiverEffect ", "Timeout")
//            }
//        })
//        try {
//            Log.e("OTPReceiverEffect ", "Lifecycle.Event.ON_RESUME")
//            Log.e("OTPReceiverEffect ", "Registering receiver")
//            context.registerReceiver(
//                otpReceiver,
//                IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION),
//                Context.RECEIVER_EXPORTED
//            )
//        } catch (e: IllegalArgumentException) {
//            Log.e("OTPReceiverEffect ", "Error in registering receiver: ${e.message}}")
//        }
//        onPauseOrDispose {
//            // add clean up for work kicked off in the ON_RESUME effect here
//            try {
//                Log.e("OTPReceiverEffect ", "Lifecycle.Event.ON_PAUSE")
//                Log.e("OTPReceiverEffect ", "Unregistering receiver")
//                context.unregisterReceiver(otpReceiver)
//            } catch (e: IllegalArgumentException) {
//                Log.e("OTPReceiverEffect ", "Error in unregistering receiver: ${e.message}}")
//            }
//        }
//    }
//}




//@Composable
//fun VerificationCodePage() {
//    var otpValue by remember { mutableStateOf(Array(7) { "" }) }  // OTP array of 6 characters
////    var remainingTime by remember { mutableStateOf(30) }  // 30 seconds countdown
////
////    LaunchedEffect(Unit) {
////        while (remainingTime > 0) {
////            delay(1000)
////            remainingTime--
////        }
////    }
//
//
//    Scaffold { padding ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(padding)
//        ) {
//            Column(
//                modifier = Modifier
//                    .padding(16.dp)
//                    .fillMaxWidth(),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    "Təsdiq Kodu",
//                    style = MaterialTheme.typography.headlineMedium,
//                    fontWeight = FontWeight.SemiBold
//                )
//                Text(
//                    "E-poçtunuza gələn təsdiq kodunu daxil edin.",
//                    style = MaterialTheme.typography.bodyMedium
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                Divider(color = Color.Gray, thickness = 0.5.dp)
//                Spacer(modifier = Modifier.height(16.dp))
//
//
//                OtpInputFields(otpValue) { index, value ->
//                    otpValue[index] = value
//                }
//
//
//
//                Spacer(modifier = Modifier.height(16.dp))
//                Text("Qalan vaxt: "
////                        + "$remainingTime saniyə"
//                    , style = MaterialTheme.typography.bodyMedium)
//                Spacer(modifier = Modifier.height(16.dp))
//                Button(
//                    onClick = {},
//                    modifier = Modifier.fillMaxWidth(0.85f),
//                    shape = RoundedCornerShape(24.dp)
//                ) {
//                    Text("Təsdiqlə")
//                }
//                Spacer(modifier = Modifier.height(16.dp))
//                TextButton(onClick = { /* Logic to resend code */ }) {
//                    Text("Kodu yenidən göndər")
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun OtpInputFields(otp: Array<String>, onValueChange: (Int, String) -> Unit) {
//    Row(
//        horizontalArrangement = Arrangement.spacedBy(8.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        otp.forEachIndexed { index, value ->
//            if (index == 3) {
//                Text(
//                    text = "-",  // Display a dash for the fourth element
//                    modifier = Modifier.padding(top = 14.dp),
//                    style = MaterialTheme.typography.headlineMedium
//                )
//            } else {
//                OutlinedTextField(
//                    value = value,
//                    onValueChange = { newValue ->
//                        if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
//                            onValueChange(index, newValue)
//                        }
//                    },
//                    singleLine = true,
//                    keyboardOptions = KeyboardOptions(
//                        keyboardType = KeyboardType.Number,
//                        imeAction = if (index == 5) ImeAction.Done else ImeAction.Next
//                    ),
//                    keyboardActions = KeyboardActions(
//                        onNext = { },
//                        onDone = { }
//                    ),
//                    textStyle = MaterialTheme.typography.headlineMedium,
//                    modifier = Modifier
//                        .width(45.dp)
//                        .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
//                )
//            }
//        }
//    }
//}


//@Preview(showBackground = true)
//@Composable
//fun VerificationCodePagePreview() {
//    MaterialTheme {
//        VerificationCodePage()
//    }
//}

















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
//    var otpValue by remember { mutableStateOf("") }
//    var isOtpFilled by remember { mutableStateOf(false) }
//    val focusRequester = remember { FocusRequester() }
//    val keyboardController = LocalSoftwareKeyboardController.current
//
//
//
//
//    LaunchedEffect(Unit) {
//        focusRequester.requestFocus()
//        keyboardController?.show()
//    }
//
//
//    (LocalView.current.context as Activity).window.statusBarColor = Color.White.toArgb()
//
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
//            Surface(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .background(Color.White)
//                    .padding(24.dp),
//                color = Color.White
//            ) {
//                OtpInputField(
//                    modifier = Modifier
//                        .padding(top = 48.dp)
//                        .focusRequester(focusRequester),
//                    otpText = otpValue,
//                    shouldCursorBlink = false,
//                    onOtpModified = { value, otpFilled ->
//                        otpValue = value
//                        isOtpFilled = otpFilled
//                        if (otpFilled) {
//                            keyboardController?.hide()
//                        }
//                    }
//                )
//            }
//
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
