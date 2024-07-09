package com.issuesolver.presentation.bottombar

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntOffset
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.issuesolver.R


@Composable
fun AnimatedNavigationBar(

) {
    val barColor: Color=Color.White
    val circleColor: Color=Color(0xFF2981FF)
    val selectedColor: Color=Color.White
    val unselectedColor: Color=Color.White
    val circleRadius = 28.dp
    val buttons = listOf(
        ButtonData("Home", painterResource(id = R.drawable.home_ic)),
        ButtonData("History", painterResource(id = R.drawable.group)),
        ButtonData("Calendar", painterResource(id = R.drawable.ph_plus)),
        ButtonData("Profile", painterResource(id = R.drawable.message)),
        ButtonData("Settings", painterResource(id = R.drawable.profile))
    )
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }
    var barSize by remember { mutableStateOf(IntSize(0, 0)) }
    val offsetStep = remember(barSize) {
        barSize.width.toFloat() / (buttons.size * 2)
    }
    val offset = remember(selectedItem, offsetStep) {
        offsetStep + selectedItem * 2 * offsetStep
    }
    val circleRadiusPx = LocalDensity.current.run { circleRadius.toPx().toInt() }
    val offsetTransition = updateTransition(offset, "offset transition")
    val animation = spring<Float>(dampingRatio = 0.5f, stiffness = Spring.StiffnessVeryLow)
    val cutoutOffset by offsetTransition.animateFloat(
        transitionSpec = {
            if (this.initialState == 0f) {
                snap()
            } else {
                animation
            }
        },
        label = "cutout offset"
    ) { it }
    val circleOffset by offsetTransition.animateIntOffset(
        transitionSpec = {
            if (this.initialState == 0f) {
                snap()
            } else {
                spring(animation.dampingRatio, animation.stiffness)
            }
        },
        label = "circle offset"
    ) {
        IntOffset(it.toInt() - circleRadiusPx, -10)  // Smaller upward movement
    }
    val barShape = remember(cutoutOffset) {
        BarShape(
            offset = cutoutOffset,
            circleRadius = 45.dp,
            cornerRadius = 25.dp
        )
    }

    Box (modifier = Modifier
        .height(76.dp)
    ){

        Circle(
            modifier = Modifier
                .offset { IntOffset(circleOffset.x, circleOffset.y - 5.dp.roundToPx()) } // Manually move up
                .zIndex(1f)
//                .padding(bottom = 10.dp)  // Add padding to push it upward from the navbar

            ,
            color = circleColor,
            radius = circleRadius,
            button = buttons[selectedItem],
            iconColor = selectedColor,
        )
        Row(
            modifier = Modifier
                .onPlaced { barSize = it.size }
                .graphicsLayer {
                    shape = barShape
                    clip = true
                }
                .fillMaxWidth()
                .background(barColor),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            buttons.forEachIndexed { index, button ->
                val isSelected = index == selectedItem
                NavigationBarItem(
                    selected = isSelected,
                    onClick = { selectedItem = index },
                    icon = {
                        val iconAlpha by animateFloatAsState(
                            targetValue = if (isSelected) 0f else 1f,
                            label = "Navbar item icon"
                        )
                        Image(
                            painter = button.icon,
                            contentDescription = button.text,
                            modifier = Modifier.alpha(iconAlpha)
                        )
                    },
//                    label = { Text(button.text) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = selectedColor,
                        selectedTextColor = selectedColor,
                        unselectedIconColor = unselectedColor,
                        unselectedTextColor = unselectedColor,
                        indicatorColor = barColor
                    )
                )
            }
        }
    }
}
@Composable
private fun Circle(
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    radius: Dp,
    button: ButtonData,
    iconColor: Color,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier

            .size(radius * 2)
            .clip(CircleShape)
            .background(color),
    ) {
        AnimatedContent(
            targetState = button.icon, label = "Bottom bar circle icon",
        ) { targetIcon ->
            Icon(targetIcon, button.text, tint = iconColor)
        }
    }
}