package com.issuesolver.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import com.issuesolver.presentation.login.daxil_ol_page.LoginPage
import com.issuesolver.presentation.login.daxil_ol_page_email.EmailVerificationPage
import com.issuesolver.presentation.login.daxil_ol_verification_code.VerificationCodePage
import com.issuesolver.presentation.navigation.AppNavigation
import com.issuesolver.ui.theme.IssueSolverTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.text.Typography.dagger

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            IssueSolverTheme {
                AppNavigation()
            }
        }
    }
}




