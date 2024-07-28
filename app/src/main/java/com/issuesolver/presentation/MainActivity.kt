package com.issuesolver.presentation

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import com.issuesolver.common.LocalSnackbarManager
import com.issuesolver.common.SnackbarManager
import com.issuesolver.presentation.myrequest.UserCard
import com.issuesolver.presentation.home.filter.FilterScreen
import com.issuesolver.presentation.home.home.HomeScreen
import com.issuesolver.presentation.myrequest.MyRequestScreen
import com.issuesolver.presentation.navigation.RootNavigationGraph
import com.issuesolver.ui.theme.IssueSolverTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        setContent {
            CompositionLocalProvider(LocalSnackbarManager provides SnackbarManager.instance) {
                IssueSolverTheme {
                    RootNavigationGraph()
//                HomeScreen()
                }
            }
        }
    }
}




