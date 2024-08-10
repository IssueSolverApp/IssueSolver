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

        installSplashScreen().setOnExitAnimationListener { splashScreenViewProvider ->
            val slideAnimation = ObjectAnimator.ofFloat(
                splashScreenViewProvider.iconView,
                "translationX",
                0f,  // Start position
                resources.displayMetrics.widthPixels.toFloat()  // End position
            ).apply {
                interpolator = LinearInterpolator()
                duration = 1000  // Duration in milliseconds
                doOnEnd {
                    splashScreenViewProvider.remove()
                }
            }
            slideAnimation.start()
        }
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

//                    HomeScreen(navController = rememberNavController(), paddingValues= PaddingValues())
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




