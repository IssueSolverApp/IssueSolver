package com.issuesolver.presentation.navigation

import BottomBarScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.issuesolver.presentation.bottombar.ButtonData


@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.MAIN_SCREEN_PAGE,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
        }
        composable(route = BottomBarScreen.MyRequest.route) {
        }
        composable(route = BottomBarScreen.NewRequest.route) {
        }
        composable(route = BottomBarScreen.Profile.route) {
        }
    }
}



fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.PROFILENEWPASSWORD.route
    ) {
        composable(route = DetailsScreen.PROFILENEWPASSWORD.route) {
        }
        composable(route = DetailsScreen.PROFILEMYACCOUNT.route) {
        }
        composable(route = DetailsScreen.PROFILEDELETEACCOUNT.route) {
        }
    }
}

sealed class DetailsScreen(val route: String) {
    object PROFILENEWPASSWORD : DetailsScreen(route = "PROFILE_NEW_PASSWORD")
    object PROFILEMYACCOUNT : DetailsScreen(route = "PROFILE_MY_ACCOUNT")
    object PROFILEDELETEACCOUNT : DetailsScreen(route = "PROFILE_DELETE_ACCOUNT")

}