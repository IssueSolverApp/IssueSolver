package com.issuesolver


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.issuesolver.presentation.bottombar.AnimatedNavigationBar


@Composable
fun TestUI(
//    navController: NavController,
//    viewModel:  = hiltViewModel(),
) {


    Scaffold(modifier = Modifier
//        .statusBarsPadding()
        .navigationBarsPadding(),
        bottomBar = {
            AnimatedNavigationBar()
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .background(color = Color.Red)
//
            ) {

            }
        })
}




