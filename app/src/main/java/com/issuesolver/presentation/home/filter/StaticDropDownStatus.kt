package com.issuesolver.presentation.home.filter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.issuesolver.R
import com.issuesolver.presentation.home.home.HomeViewModel


@Composable
fun StaticDropDownStatus(category: String, placeHolder: String, list:List<String>,viewModel: HomeViewModel) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded) R.drawable.vector__3_ else R.drawable.vector__2_



    Column(Modifier.padding(start = 20.dp, end = 20.dp, top = 16.dp)) {
        Text(
            text = placeHolder,
            fontSize = 15.sp,
            color = Color(0xFF000B1B)
        )

        TextField(
            readOnly = true,
            value = selectedText,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                }
                .clip(MaterialTheme.shapes.medium),
            placeholder = { Text(category, color = Color(0xFF9D9D9D)) },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "contentDescription",
                    modifier = Modifier.clickable { expanded = !expanded },
                    tint = Color.Unspecified
                )            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            list.forEach { label ->

                DropdownMenuItem(
                    text = { Text(text = label) },
                    onClick = {
                        selectedText = label
                        expanded = false
                        viewModel.selectStatus(label)

                    }
                )
            }
        }
    }
}