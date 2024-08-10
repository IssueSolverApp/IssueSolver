package com.issuesolver.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.issuesolver.presentation.newrequest.RequestInfoScreen


fun NavGraphBuilder.newRequestsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.NewRequestsGraph,
        startDestination = NewRequestScreen.RequestInfoScreen.route
    ) {
        composable(route = NewRequestScreen.RequestInfoScreen.route) {
            RequestInfoScreen(navController = navController)
        }
    }
}