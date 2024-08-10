package com.issuesolver.presentation.bottombar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.issuesolver.presentation.navigation.MainNavGraph


@SuppressLint("RememberReturnType")
@Composable
fun MainScreen(
    navController: NavHostController,
    homeNavController: NavHostController = rememberNavController()

) {
    val navBackStackEntry by homeNavController.currentBackStackEntryAsState()
    val currentRoute by remember(navBackStackEntry) {
        derivedStateOf {
            navBackStackEntry?.destination?.route
        }
    }

    Scaffold(

        bottomBar = {
            AnimatedNavigationBar(navController = homeNavController)        }
    ) {padding->
        MainNavGraph(navController,homeNavController, paddingValues = padding)
    }
}




