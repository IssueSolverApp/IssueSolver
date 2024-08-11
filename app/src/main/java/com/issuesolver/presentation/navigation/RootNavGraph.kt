package com.issuesolver.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.issuesolver.presentation.bottombar.MainScreen

@Composable
fun RootNavigationGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(navController = navController)
        composable(route = Graph.MAIN_SCREEN_PAGE) {
            MainScreen(navController = navController)
        }
        homeNavGraph(navController = navController)
        myRequestsNavGraph(navController = navController)
        newRequestsNavGraph(navController = navController)
        profileNavGraph(navController = navController)
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val MAIN_SCREEN_PAGE = "main_screen_graph"
    const val HomeGraph="homeGraph"
    const val MyRequestsGraph="myRequestsGraph"
    const val ProfileDeleteAccountGraph="profileDeleteAccountGraph"
    const val ProfileNewPasswordGraph="profileNewPasswordGraph"
    const val ProfileMyAccountGraph="profileMyAccountGraph"
    const val NewRequestsGraph="newRequestsGraph"

}
sealed class AuthScreen(val route: String) {
    object SPLASH : AuthScreen(route = "SPLASH")
    object Login : AuthScreen(route = "LOGIN")
    object EmailVerification : AuthScreen(route = "EMAIL_VERIFICATION")
    object Otp : AuthScreen(route = "OTP")
    object PasswordChange : AuthScreen(route = "PASSWORD_CHANGE")
    object Register : AuthScreen(route = "REGISTER")
    object RegisterOtp : AuthScreen(route = "REGISTER_OTP")
}

sealed class HomeScreen(val route: String){
    object HomeFilterScreen : HomeScreen(route = "Home_Filter_Screen")
    object DetailsById : HomeScreen(route = "Details_By_Id")
}
sealed class MyRequestScreen(val route: String){
    object RequestById : MyRequestScreen(route = "Request_By_Id")

}
sealed class NewRequestScreen(val route: String){
    object RequestInfoScreen : NewRequestScreen(route = "REQUEST_INFO_SCREEN")
}
sealed class ProfileScreen(val route: String){
    object ProfileNewPassword : ProfileScreen(route = "PROFILE_NEW_PASSWORD")
    object ProfileMyAccount : ProfileScreen(route = "PROFILE_MY_ACCOUNT")
    object ProfileDeleteAccount : ProfileScreen(route = "PROFILE_DELETE_ACCOUNT")
}

//
//@Composable
//fun RootNavigationGraph() {
//    val navController = rememberNavController()
//
//    NavHost(
//        navController = navController,
//        route = Graph.ROOT,
//        startDestination = Graph.AUTHENTICATION
//    ) {
//        authNavGraph(navController)
//        composable(route = Graph.MAIN_SCREEN_PAGE) {
//            MainScreen(navController)
//        }
//    }
//}
