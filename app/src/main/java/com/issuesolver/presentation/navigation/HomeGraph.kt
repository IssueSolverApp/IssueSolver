package com.issuesolver.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.issuesolver.presentation.home.filter.FilterScreen
import com.issuesolver.presentation.home.home.DetailedRequestScreen
import com.issuesolver.presentation.newrequest.RequestInfoScreen


fun NavGraphBuilder.homeNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.HomeGraph,
        startDestination = HomeScreen.HomeFilterScreen.route
    ) {
        composable(route = HomeScreen.HomeFilterScreen.route,
            enterTransition = { slideInFromRight() },
            exitTransition = { slideOutToLeft() },
            popEnterTransition = { slideInFromLeft() },
            popExitTransition = { slideOutToRight() }) {
            FilterScreen(navController = navController)
        }

        composable(route = HomeScreen.DetailsById.route + "/{requestId}",
            enterTransition = { slideInFromRight() },
            exitTransition = { slideOutToLeft() },
            popEnterTransition = { slideInFromLeft() },
            popExitTransition = { slideOutToRight() }) {
            val id = it.arguments?.getString("requestId")
            id?.let { it1 ->  DetailedRequestScreen(navController = navController, id= it1) }
        }
    }
}