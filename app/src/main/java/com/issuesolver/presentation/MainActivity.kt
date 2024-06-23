package com.issuesolver.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.core.view.WindowCompat
import com.issuesolver.presentation.login.daxil_ol_page.LoginPage
import com.issuesolver.presentation.login.daxil_ol_page_email.EmailVerificationPage
import com.issuesolver.presentation.login.daxil_ol_verification_code.VerificationCodePage
import com.issuesolver.presentation.login.qeydiyyat_page.RegisterPage
import com.issuesolver.ui.theme.IssueSolverTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.text.Typography.dagger

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        WindowCompat.setDecorFitsSystemWindows(window, false)


        setContent {
            IssueSolverTheme {

//                val scrollState = rememberScrollState()
//                val coroutineScope = rememberCoroutineScope()
//                val keyboardHeight = WindowInsets.ime.getBottom(LocalDensity.current)
//
//                LaunchedEffect(key1 = keyboardHeight) {
//                    coroutineScope.launch {
//                        scrollState.scrollBy(keyboardHeight.toFloat())
//                    }
//                }


                    RegisterPage()


            }
        }
    }
}



//@Composable
//fun KeyboardAware(
//    content: @Composable () -> Unit
//) {
//    Box(modifier = Modifier.imePadding()) {
//        content()
//    }
//}




