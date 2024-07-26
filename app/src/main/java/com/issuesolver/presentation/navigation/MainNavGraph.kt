package com.issuesolver.presentation.navigation

import BottomBarScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.issuesolver.TestUI
import com.issuesolver.presentation.bottombar.ButtonData
import com.issuesolver.presentation.newrequest.RequestInfoScreen
import com.issuesolver.presentation.home.filter.FilterScreen
import com.issuesolver.presentation.newrequest.RequestScreen
import com.issuesolver.presentation.home.home.HomeScreen
import com.issuesolver.presentation.profile.enter_password.DeleteAccountScreen
import com.issuesolver.presentation.profile.my_account.MyAccountScreen
import com.issuesolver.presentation.profile.new_password.NewPasswordScreen
import com.issuesolver.presentation.profile.profile.ProfileScreen


@Composable
fun MainNavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        route = Graph.MAIN_SCREEN_PAGE,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route,
            ) {
            HomeScreen(navController)
        }
        composable(route = BottomBarScreen.MyRequest.route) {
            TestUI()
        }
        composable(route = BottomBarScreen.NewRequest.route) {
            RequestScreen(navController, paddingValues)
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen(navController, paddingValues)

        }
        composable(route = DetailsScreen.ProfileNewPassword.route) {
            NewPasswordScreen(navController)
        }
        composable(route = DetailsScreen.ProfileMyAccount.route) {
            MyAccountScreen(navController)
        }
        composable(route = DetailsScreen.ProfileDeleteAccount.route) {
            DeleteAccountScreen(navController)
        }
        composable(route = DetailsScreen.HomeFilterScreen.route) {
            FilterScreen(navController)
        }

        composable(route = DetailsScreen.RequestInfoScreen.route) {
            RequestInfoScreen(navController)
        }

    }
}



//fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
//    navigation(
//        route = Graph.DETAILS,
//        startDestination = DetailsScreen.ProfileNewPassword.route
//    ) {
////        composable(route = DetailsScreen.ProfileNewPassword.route) {
////            NewPasswordScreen(navController)
////        }
////        composable(route = DetailsScreen.ProfileMyAccount.route) {
////            MyAccountScreen(navController)
////        }
////        composable(route = DetailsScreen.ProfileDeleteAccount.route) {
////            DeleteAccountScreen(navController)
////        }
//    }
//}

sealed class DetailsScreen(val route: String) {
    object ProfileNewPassword : DetailsScreen(route = "PROFILE_NEW_PASSWORD")
    object ProfileMyAccount : DetailsScreen(route = "PROFILE_MY_ACCOUNT")
    object ProfileDeleteAccount : DetailsScreen(route = "PROFILE_DELETE_ACCOUNT")
    object RequestInfoScreen : DetailsScreen(route = "REQUEST_INFO_SCREEN")


    object HomeFilterScreen : DetailsScreen(route = "Home_Filter_Screen")


}

//@Composable
//inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
//    val navGraphRoute = destination.parent?.route ?: return viewModel()
//    val parentEntry = remember(this) {
//        navController.getBackStackEntry(navGraphRoute)
//    }
//    return viewModel(parentEntry)
//}