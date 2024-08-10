package com.issuesolver.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.issuesolver.presentation.profile.enter_password.DeleteAccountScreen
import com.issuesolver.presentation.profile.my_account.MyAccountScreen
import com.issuesolver.presentation.profile.new_password.NewPasswordScreen


fun NavGraphBuilder.profileDeleteAccountNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.ProfileDeleteAccountGraph,
        startDestination = ProfileScreen.ProfileDeleteAccount.route
    ) {

        composable(route = ProfileScreen.ProfileDeleteAccount.route) {
            DeleteAccountScreen(navController = navController)
        }
    }
}
fun NavGraphBuilder.profileNewPasswordNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.ProfileNewPasswordGraph,
        startDestination = ProfileScreen.ProfileNewPassword.route
    ) {
        composable(route = ProfileScreen.ProfileNewPassword.route) {
            NewPasswordScreen(navController = navController)
        }

    }
}
fun NavGraphBuilder.profileMyAccountNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.ProfileMyAccountGraph,
        startDestination = ProfileScreen.ProfileMyAccount.route
    ) {

        composable(route = ProfileScreen.ProfileMyAccount.route) {
            MyAccountScreen(navController = navController)
        }

    }
}