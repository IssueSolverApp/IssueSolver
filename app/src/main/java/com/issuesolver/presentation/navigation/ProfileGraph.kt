package com.issuesolver.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.issuesolver.presentation.profile.enter_password.DeleteAccountScreen
import com.issuesolver.presentation.profile.my_account.MyAccountScreen
import com.issuesolver.presentation.profile.new_password.NewPasswordScreen


fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.ProfileMyAccountGraph,
        startDestination = ProfileScreen.ProfileMyAccount.route
    ) {
        composable(route = ProfileScreen.ProfileMyAccount.route,
            enterTransition = { slideInFromRight() },
            exitTransition = { slideOutToLeft() },
            popEnterTransition = { slideInFromLeft() },
            popExitTransition = { slideOutToRight() }) {
            MyAccountScreen(navController = navController)
        }
        composable(route = ProfileScreen.ProfileNewPassword.route,
            enterTransition = { slideInFromRight() },
            exitTransition = { slideOutToLeft() },
            popEnterTransition = { slideInFromLeft() },
            popExitTransition = { slideOutToRight() }) {
            NewPasswordScreen(navController = navController)
        }
        composable(route = ProfileScreen.ProfileDeleteAccount.route,
            enterTransition = { slideInFromRight() },
            exitTransition = { slideOutToLeft() },
            popEnterTransition = { slideInFromLeft() },
            popExitTransition = { slideOutToRight() }) {
            DeleteAccountScreen(navController = navController)
        }
    }
}