package com.issuesolver.common

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize

@SuppressLint("ModifierFactoryUnreferencedReceiver")
fun Modifier.shimmerEffect(darkShimmer: Boolean = true): Modifier = composed {
    var size by remember { mutableStateOf(IntSize.Zero) }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(animation = tween(durationMillis = 1000)), label = ""
    )
    background(
        brush = Brush.linearGradient(
            colors = listOf(
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.01f),
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.02f),
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.01f)
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width, size.height.toFloat())
        )
    ).onGloballyPositioned { coordinates ->
        size = coordinates.size
    }
}
