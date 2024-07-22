package com.issuesolver.presentation.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.issuesolver.R
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun Splash() {
    val animatableX1 = remember { Animatable(0f) }
    val animatableX2 = remember { Animatable(0f) }
    val scope = rememberCoroutineScope()
    var boxWidth by remember { mutableStateOf(0f) }
    var rowWidth by remember { mutableStateOf(0f) }

    LaunchedEffect(Unit) {
        scope.launch {
            animatableX1.animateTo(
                targetValue = 0f,
                animationSpec = tween(
                    durationMillis = 2000,
                    easing = LinearEasing
                )
            )
        }
        scope.launch {
            animatableX2.animateTo(
                targetValue = 0f,
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = LinearEasing
                )
            )
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
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    rowWidth = coordinates.size.width.toFloat()
                }
                .offset {
                    IntOffset(
                        x = ((boxWidth - rowWidth) / 2).roundToInt(),
                        y = 0
                    )
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ellipse_9),
                contentDescription = "ellipse_9",
                modifier = Modifier
                    .size(32.dp)
                    .offset {
                        IntOffset(
                            x = (animatableX1.value * (boxWidth - rowWidth) / 2).roundToInt(),
                            y = 0
                        )
                    }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Issue Solver",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 32.sp,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Start,
                color = Color(0xFF2981FF),
                modifier = Modifier
                    .offset {
                        IntOffset(
                            x = (animatableX2.value * (boxWidth - rowWidth) / 2).roundToInt(),
                            y = 0
                        )
                    }
            )
        }
    }
}