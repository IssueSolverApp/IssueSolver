package com.issuesolver.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.issuesolver.presentation.login.daxil_ol_page.LoginPage
import com.issuesolver.presentation.login.daxil_ol_page_email.EmailVerificationPage
import com.issuesolver.presentation.login.daxil_ol_verification_code.VerificationCodePage
import com.issuesolver.presentation.login.password_change_page.PasswordChangePage
import com.issuesolver.presentation.login.qeydiyyat_page.RegisterOtpCodePage
import com.issuesolver.presentation.login.qeydiyyat_page.RegisterPage


fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {

//        composable(route = AuthScreen.SPLASH.route) {
//            SplashScreen(navController = navController)
//        }

        composable(route = AuthScreen.Login.route) {
            LoginPage(navController = navController)
        }
        composable(route = AuthScreen.EmailVerification.route) {
            EmailVerificationPage(navController = navController)
        }
        composable(route = AuthScreen.Otp.route) {
            VerificationCodePage(navController = navController)
        }
        composable(route = AuthScreen.PasswordChange.route) {
            PasswordChangePage(navController = navController)
        }
        composable(route = AuthScreen.Register.route) {
            RegisterPage(navController)
        }
        composable(route = AuthScreen.RegisterOtp.route) {navBackStack ->
            val email = navBackStack.arguments?.getString("id")

            RegisterOtpCodePage(navController = navController, email = email)
        }
    }
}

sealed class AuthScreen(val route: String) {
//    object SPLASH : AuthScreen(route = "SPLASH")
    object Login : AuthScreen(route = "LOGIN")
    object EmailVerification : AuthScreen(route = "EMAIL_VERIFICATION")
    object Otp : AuthScreen(route = "OTP")
    object PasswordChange : AuthScreen(route = "PASSWORD_CHANGE")
    object Register : AuthScreen(route = "REGISTER")
    object RegisterOtp : AuthScreen(route = "REGISTER_OTP")
}