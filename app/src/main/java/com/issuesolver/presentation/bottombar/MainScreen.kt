package com.issuesolver.presentation.bottombar



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.issuesolver.R

@Composable
fun MainScreen() {
    Scaffold( modifier = Modifier
//        .statusBarsPadding()
        .navigationBarsPadding(),
        bottomBar = {
            AnimatedNavigationBar(
                buttons = listOf(
                    ButtonData("Home", painterResource(id = R.drawable.home_ic)),
                    ButtonData("History", painterResource(id = R.drawable.group)),
                    ButtonData("Calendar", painterResource(id = R.drawable.ph_plus)),
                    ButtonData("Profile", painterResource(id = R.drawable.message)),
                    ButtonData("Settings", painterResource(id = R.drawable.profile))
                ),
                barColor = Color(0xFF6200EE),
                circleColor = Color.White,
                selectedColor = Color.Gray,
                unselectedColor = Color.LightGray,
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues)
        ) {
            Text(
                text = "Main Content",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
        }
    }
}