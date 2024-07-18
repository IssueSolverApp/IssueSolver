package com.issuesolver.presentation.bottombar

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.issuesolver.presentation.navigation.MainNavGraph


@Composable
fun MainScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        bottomBar = {
            AnimatedNavigationBar(navController)
        },
        content = { padding ->
            MainNavGraph(navController = navController, paddingValues = padding)
        }
    )
}



