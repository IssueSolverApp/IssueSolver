package com.issuesolver.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally

fun slideInFromRight(animationDuration: Int = 300): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { fullWidth -> fullWidth },
        animationSpec = tween(durationMillis = animationDuration)
    ) + fadeIn(animationSpec = tween(durationMillis = animationDuration))
}

fun slideOutToLeft(animationDuration: Int = 300): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { fullWidth -> -fullWidth },
        animationSpec = tween(durationMillis = animationDuration)
    ) + fadeOut(animationSpec = tween(durationMillis = animationDuration))
}

fun slideInFromLeft(animationDuration: Int = 300): EnterTransition {
    return slideInHorizontally(
        initialOffsetX = { fullWidth -> -fullWidth },
        animationSpec = tween(durationMillis = animationDuration)
    ) + fadeIn(animationSpec = tween(durationMillis = animationDuration))
}

fun slideOutToRight(animationDuration: Int = 300): ExitTransition {
    return slideOutHorizontally(
        targetOffsetX = { fullWidth -> fullWidth },
        animationSpec = tween(durationMillis = animationDuration)
    ) + fadeOut(animationSpec = tween(durationMillis = animationDuration))
}
