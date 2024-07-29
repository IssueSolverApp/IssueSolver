package com.issuesolver.presentation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.issuesolver.presentation.bottombar.MainScreen
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

//                    RootNavigationGraph()

                    val accessToken = sharedPreferences.getString("access_token", null)

                    if (accessToken.isNullOrEmpty()) {
                        // Если токен отсутствует, показываем экран логина
                         // Замените на ваш экран логина
                        RootNavigationGraph()
                    } else {
                        // Если токен есть, запускаем основной экран

                        MainScreen()
                    }
            }
        }



//        setContent {
//            val navController = rememberNavController()
//            NavHost(
//                navController = navController,
//                startDestination = Graph.AUTHENTICATION
//            ) {
//                authNavGraph(navController)
//                composable(route = Graph.MAIN_SCREEN_PAGE) {
//                    MainScreen(navController)
//                }
//            }
//        }
    }
}




