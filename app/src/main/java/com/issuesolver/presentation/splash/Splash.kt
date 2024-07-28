package com.issuesolver.presentation.splash

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.animation.core.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import com.issuesolver.R
import com.issuesolver.presentation.navigation.AuthScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Splash(navController: NavHostController) {
    val animatableX = remember { Animatable(0f) }  // Initial animation state
    val scope = rememberCoroutineScope()
    var boxWidth by remember { mutableStateOf(0f) }

    LaunchedEffect(Unit) {
        scope.launch {
            animatableX.animateTo(
                targetValue = 0.5f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessVeryLow
                )
            )
            delay(1000)
            navController.navigate(AuthScreen.Login.route) {
                popUpTo(navController.graph.startDestinationId)
                launchSingleTop = true
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onGloballyPositioned { coordinates ->
                boxWidth = coordinates.size.width.toFloat()
            },
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .graphicsLayer {
                    translationX = animatableX.value * boxWidth - (boxWidth / 2)
                }
        ) {
            Image(
                painter = painterResource(R.drawable.ellipse_9),
                contentDescription = "Animated Circle",
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Issue Solver",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 32.sp,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center,
                color = Color(0xFF2981FF)
            )
        }
    }
}
