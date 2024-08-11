package com.issuesolver.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.issuesolver.presentation.myrequest.OpenedMyRequestScreen


fun NavGraphBuilder.myRequestsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.MyRequestsGraph,
        startDestination = MyRequestScreen.RequestById.route
    ) {
        composable(route = MyRequestScreen.RequestById.route + "/{requestId}",
            enterTransition = { slideInFromRight() },
            exitTransition = { slideOutToLeft() },
            popEnterTransition = { slideInFromLeft() },
            popExitTransition = { slideOutToRight() }) {
            val id = it.arguments?.getString("requestId")
            //val likeSuccess = it.arguments?.getString("likeSuccess")
           // val email = navBackStack.arguments?.getString("id")
            id?.let { it1 ->  OpenedMyRequestScreen(navController = navController, id= it1) }
        }
    }
}