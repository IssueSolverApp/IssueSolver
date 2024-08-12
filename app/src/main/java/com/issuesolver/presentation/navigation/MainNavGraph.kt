package com.issuesolver.presentation.navigation

import BottomBarScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
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
import com.issuesolver.presentation.home.home.DetailedRequestScreen
import com.issuesolver.presentation.newrequest.RequestScreen
import com.issuesolver.presentation.home.home.HomeScreen
import com.issuesolver.presentation.myrequest.MyRequestScreen
import com.issuesolver.presentation.myrequest.MyRequestScreen
import com.issuesolver.presentation.myrequest.OpenedMyRequestScreen
import com.issuesolver.presentation.profile.enter_password.DeleteAccountScreen
import com.issuesolver.presentation.profile.my_account.MyAccountScreen
import com.issuesolver.presentation.profile.new_password.NewPasswordScreen
import com.issuesolver.presentation.profile.profile.ProfileScreen


@Composable
fun MainNavGraph(navController: NavHostController,homeNavController: NavHostController, paddingValues: PaddingValues,

) {
    NavHost(
        navController = homeNavController,
        route = Graph.MAIN_SCREEN_PAGE,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route=BottomBarScreen.Home.route) {
            HomeScreen(navController = navController,paddingValues)
        }
        composable(route = BottomBarScreen.MyRequest.route) {
            MyRequestScreen(navController = navController, paddingValues)
        }
        composable(route = BottomBarScreen.NewRequest.route) {

            RequestScreen(navController = navController, paddingValues)
        }
        composable(route = BottomBarScreen.Profile.route) {
            ProfileScreen(navController = navController, paddingValues)
        }

    }
}