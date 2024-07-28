package com.issuesolver.presentation.bottombar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.issuesolver.common.SnackbarManager
import com.issuesolver.common.TopSnackbarHost
import com.issuesolver.presentation.navigation.MainNavGraph


@SuppressLint("RememberReturnType")
@Composable
fun MainScreen(navController: NavHostController = rememberNavController(), snackbarManager: SnackbarManager = SnackbarManager.instance) {
    val currentRoute = remember { mutableStateOf(BottomBarScreen.Profile.route) }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRouteValue = navBackStackEntry?.destination?.route

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = snackbarManager) {
        snackbarManager.messages.collect { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    LaunchedEffect(currentRouteValue) {
        currentRoute.value = currentRouteValue ?: BottomBarScreen.Profile.route
    }




    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        snackbarHost = { TopSnackbarHost(snackbarHostState) },

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
            MainNavGraph(navController = navController, paddingValues = padding,snackbarManager=snackbarManager)
        }
    )
}




