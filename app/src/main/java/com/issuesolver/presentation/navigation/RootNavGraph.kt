package com.issuesolver.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.issuesolver.presentation.bottombar.MainScreen

@Composable
fun RootNavigationGraph() {
    val navController = rememberNavController()
    val navControllerBottomBar = rememberNavController()


    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(navController = navController)
        composable(route = Graph.MAIN_SCREEN_PAGE) {
            MainScreen()
        }
        detailsNavGraph(navController = navControllerBottomBar)

    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val MAIN_SCREEN_PAGE = "main_screen_graph"
    const val DETAILS = "details_graph"
}