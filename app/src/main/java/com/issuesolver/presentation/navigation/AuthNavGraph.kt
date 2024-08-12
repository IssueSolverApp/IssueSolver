package com.issuesolver.presentation.navigation

import android.window.SplashScreen
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
import com.issuesolver.presentation.splash.Splash


fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.SPLASH.route
    ) {

        composable(route = AuthScreen.SPLASH.route) {
            Splash(navController = navController)
        }

        composable(route = AuthScreen.Login.route,
            enterTransition = { slideInFromRight() },
            exitTransition = { slideOutToLeft() },
            popEnterTransition = { slideInFromLeft() },
            popExitTransition = { slideOutToRight() }) {
            LoginPage(navController = navController)
        }
        composable(route = AuthScreen.EmailVerification.route,
            enterTransition = { slideInFromRight() },
            exitTransition = { slideOutToLeft() },
            popEnterTransition = { slideInFromLeft() },
            popExitTransition = { slideOutToRight() }) {
            EmailVerificationPage(navController = navController)
        }
        composable(route = AuthScreen.Otp.route,
            enterTransition = { slideInFromRight() },
            exitTransition = { slideOutToLeft() },
            popEnterTransition = { slideInFromLeft() },
            popExitTransition = { slideOutToRight() }) {
            VerificationCodePage(navController = navController)
        }
        composable(route = AuthScreen.PasswordChange.route,
            enterTransition = { slideInFromRight() },
            exitTransition = { slideOutToLeft() },
            popEnterTransition = { slideInFromLeft() },
            popExitTransition = { slideOutToRight() }) {
            PasswordChangePage(navController = navController)
        }
        composable(route = AuthScreen.Register.route,
            enterTransition = { slideInFromRight() },
            exitTransition = { slideOutToLeft() },
            popEnterTransition = { slideInFromLeft() },
            popExitTransition = { slideOutToRight() }) {
            RegisterPage(navController = navController)
        }
        composable(route = AuthScreen.RegisterOtp.route+ "/{id}",
            enterTransition = { slideInFromRight() },
            exitTransition = { slideOutToLeft() },
            popEnterTransition = { slideInFromLeft() },
            popExitTransition = { slideOutToRight() }) {navBackStack ->
            val email = navBackStack.arguments?.getString("id")

            RegisterOtpCodePage(navController = navController, email = email)
        }
    }
}
