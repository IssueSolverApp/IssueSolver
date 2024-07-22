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
import androidx.compose.foundation.layout.fillMaxWidth
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
    val scope = rememberCoroutineScope()
    var boxWidth by remember { mutableStateOf(1f) } // Avoid division by zero
    val imageAnimatable = remember { Animatable(0f) } // Start as a float representing pixels
    val textAnimatable = remember { Animatable(0f) }  // Start as a float representing pixels

    LaunchedEffect(boxWidth) {
        scope.launch {
            // Animate image to its end position with a padding of 10.dp from the right
            imageAnimatable.animateTo(
                targetValue = ((boxWidth/2)+60) ,  // Image width 50.dp + end padding 10.dp
                animationSpec = tween(
                    durationMillis = 2000
                )
            )
            // Animate text to center of the screen
            textAnimatable.animateTo(
                targetValue = (boxWidth / 2),
                animationSpec = tween(
                    durationMillis = 2000
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
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ellipse_9),
                contentDescription = "ellipse_9",
                modifier = Modifier
                    .padding(start=70.dp)
                    .offset { IntOffset(imageAnimatable.value.roundToInt() - 190.dp.toPx().roundToInt(), 0) }
                    .size(32.dp)

                ,



                )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Issue Solver",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 32.sp,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center,
                color = Color(0xFF2981FF),
                modifier = Modifier
                    .offset { IntOffset(imageAnimatable.value.roundToInt() - 190.dp.toPx().roundToInt(), 0) },

            )
        }
    }
}