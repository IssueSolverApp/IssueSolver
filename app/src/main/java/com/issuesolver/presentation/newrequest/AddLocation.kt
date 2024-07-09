package com.issuesolver.presentation.newrequest

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize

@Composable
fun AddLocation() {

    var selectedText by remember { mutableStateOf("") }

    Column{

        Text(
            text = "Problemin baş verdiyi yer",
            fontSize = 15.sp,
            color = Color(0xFF000B1B),
            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
        )

        TextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 20.dp, end = 20.dp)
                .clip(MaterialTheme.shapes.medium),
            placeholder = {
                Text(
                    "Ünvanı daxil edin",
                    color = Color(0xFF9D9D9D),
                    fontSize = 15.sp,
                )
            },

            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text(
                text = "Max: 50 simvol",
                fontSize = 15.sp,
                color = Color(0xFF9D9D9D),
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )
            Text(
                text = "Sorğu necə paylaşılır?",
                fontSize = 15.sp,
                color = Color(0xFF2981FF),
                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
            )
        }

    }


}