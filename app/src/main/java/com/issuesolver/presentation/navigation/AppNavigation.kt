package com.issuesolver.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.issuesolver.presentation.login.daxil_ol_page.LoginPage
import com.issuesolver.presentation.login.daxil_ol_page_email.EmailVerificationPage
import com.issuesolver.presentation.login.daxil_ol_verification_code.VerificationCodePage
import com.issuesolver.presentation.login.password_change_page.PasswordChangePage
import com.issuesolver.presentation.login.qeydiyyat_page.RegisterOtpCodePage
import com.issuesolver.presentation.login.qeydiyyat_page.RegisterPage

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.LOGIN) {
        composable(Routes.LOGIN) { LoginPage(navController) }
        composable(Routes.EMAIL_VERIFICATION) { EmailVerificationPage(navController) }
        composable(Routes.OTP) { VerificationCodePage(navController) }
        composable(Routes.PASSWORD_CHANGE) { PasswordChangePage(navController) }
        composable(Routes.REGISTER) { RegisterPage(navController) }
        composable(Routes.REGISTER_OTP) { RegisterOtpCodePage(navController = navController) }

    }
}