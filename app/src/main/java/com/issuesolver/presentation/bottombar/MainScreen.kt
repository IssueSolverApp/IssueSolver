package com.issuesolver.presentation.bottombar

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.issuesolver.presentation.navigation.MainNavGraph


@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    val currentRoute = remember { mutableStateOf(BottomBarScreen.Profile.route) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRouteValue = navBackStackEntry?.destination?.route

    LaunchedEffect(currentRouteValue) {
        currentRoute.value = currentRouteValue ?: BottomBarScreen.Profile.route
    }


    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        bottomBar = {
            if (currentRoute.value in listOf(
                    BottomBarScreen.Home.route,
                    BottomBarScreen.MyRequest.route,
                    BottomBarScreen.NewRequest.route,
                    BottomBarScreen.Profile.route
                )) {
                AnimatedNavigationBar(navController)
            }
        },
        content = { padding ->
            MainNavGraph(navController = navController, paddingValues = padding)
        }
    )
}




