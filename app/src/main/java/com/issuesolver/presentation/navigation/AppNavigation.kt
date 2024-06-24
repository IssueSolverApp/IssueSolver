package com.issuesolver.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.issuesolver.presentation.login.login_page.LoginPage
import com.issuesolver.presentation.login.email_verification_page.EmailVerificationPage
import com.issuesolver.presentation.login.verification_code_page.VerificationCodePage
import com.issuesolver.presentation.login.password_change_page.PasswordChangePage

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.LOGIN) {
        composable(Routes.LOGIN) { LoginPage(navController) }
        composable(Routes.EMAIL_VERIFICATION) { EmailVerificationPage(navController) }
        composable(Routes.OTP) { VerificationCodePage(navController) }
        composable(Routes.PASSWORD_CHANGE) { PasswordChangePage(navController) }
    }
}