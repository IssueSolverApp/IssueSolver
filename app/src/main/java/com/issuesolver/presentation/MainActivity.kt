package com.issuesolver.presentation

import android.animation.ObjectAnimator
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.WindowManager
import android.view.animation.LinearInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.issuesolver.presentation.bottombar.MainScreen
import com.issuesolver.presentation.home.filter.FilterScreen
import com.issuesolver.presentation.home.home.HomeScreen
import com.issuesolver.presentation.navigation.Graph
import com.issuesolver.presentation.navigation.RootNavigationGraph
import com.issuesolver.presentation.navigation.authNavGraph
import com.issuesolver.ui.theme.IssueSolverTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val sharedPreferences: SharedPreferences by lazy {
            getSharedPreferences("your_shared_prefs", Context.MODE_PRIVATE)
        }

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        setContent {
                IssueSolverTheme {
                    val navController = rememberNavController()

                    val accessToken = sharedPreferences.getString("access_token", null)

                    if (accessToken.isNullOrEmpty()) {
                        RootNavigationGraph()
                    } else {
                        MainScreen(navController = navController)
                    }

            }
        }
    }
}




