package com.issuesolver.presentation.newrequest

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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

//@Composable
//fun AddLocation(viewModel: RequestScreenViewModel) {
//
//    val location by viewModel.location.collectAsState()
//    var isError by remember { mutableStateOf(false) }
//    val maxLength = 50
//
//    Column {
//
//        Text(
//            text = "Problemin baş verdiyi yer",
//            fontSize = 15.sp,
//            color = Color(0xFF000B1B),
//            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
//        )
//
//        CustomOutlinedTextField(
//            value = location,
//            onValueChange = { newText ->
//                val textLength = newText.length
//                // Обновляем состояние только если текст в пределах максимальной длины
//                if (textLength <= maxLength) {
//                    viewModel.updateLocation(newText)
//                    isError = textLength < 5
//                }
//            },
//            isError = isError,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 8.dp, start = 20.dp, end = 20.dp),
//            placeholder = {
//                Text(
//                    "Ünvanı daxil edin",
//                    color = Color(0xFF9D9D9D),
//                    fontSize = 15.sp
//                )
//            },
//            errorColor = Color.Red,
//            borderThickness = 1.dp // Adjust thickness here
//        )
//
//        Row(
//            horizontalArrangement = Arrangement.SpaceBetween,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 8.dp)
//        ) {
//            Text(
//                text = "Min:5-Max:50 simvol",
//                fontSize = 15.sp,
//                color = Color(0xFF9D9D9D),
//                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
//            )
//            Text(
//                text = "Sorğu necə paylaşılır?",
//                fontSize = 15.sp,
//                color = Color(0xFF2981FF),
//                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
//            )
//        }
//    }
//}


@Composable
fun AddLocation(viewModel: RequestScreenViewModel) {

    val location by viewModel.location.collectAsState()
    var isError by remember { mutableStateOf(false) }
    val maxLength = 50

    Column {

        Text(
            text = "Problemin baş verdiyi yer",
            fontSize = 15.sp,
            color = Color(0xFF000B1B),
            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
        )

        TextField(
            value = location,
            onValueChange = { newText ->
                val textLength = newText.length
                // Обновляем состояние только если текст в пределах максимальной длины
                if (textLength <= maxLength) {
                    viewModel.updateLocation(newText)
                    isError = textLength < 5
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 20.dp, end = 20.dp)
                .then(
                if (isError) Modifier.border(
                    1.dp,
                    Color.Red,
                    RoundedCornerShape(12.dp)
                )
                else Modifier.border(
                    1.dp,
                    Color.White,
                    RoundedCornerShape(12.dp)
                )
                ),
            placeholder = {
                Text(
                    "Ünvanı daxil edin",
                    color = Color(0xFF9D9D9D),
                    fontSize = 15.sp
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor =  Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = MaterialTheme.shapes.medium
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text(
                text = "Min:5-Max:50 simvol",
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



//--------------------------------------------------------------------------------------------------



//
//
//@Composable
//fun AddLocation(viewModel: RequestScreenViewModel) {
//
//
//
//    val location by viewModel.location.collectAsState()
//
//
//
//    Column{
//
//        Text(
//            text = "Problemin baş verdiyi yer",
//            fontSize = 15.sp,
//            color = Color(0xFF000B1B),
//            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
//        )
//
//        TextField(
//            value = location,
//            onValueChange = {
//
//                viewModel.updateLocation(it)
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 8.dp, start = 20.dp, end = 20.dp)
//                .clip(MaterialTheme.shapes.medium),
//            placeholder = {
//                Text(
//                    "Ünvanı daxil edin",
//                    color = Color(0xFF9D9D9D),
//                    fontSize = 15.sp,
//                )
//            },
//
//            colors = TextFieldDefaults.colors(
//                focusedContainerColor = Color.White,
//                unfocusedContainerColor = Color.White,
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent
//            )
//        )
//
//        Row(
//            horizontalArrangement = Arrangement.SpaceBetween,
//            modifier = Modifier.fillMaxWidth()
//                .padding(top = 8.dp)
//        ) {
//            Text(
//                text = "Min:5-Max:50 simvol",
//                fontSize = 15.sp,
//                color = Color(0xFF9D9D9D),
//                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
//            )
//            Text(
//                text = "Sorğu necə paylaşılır?",
//                fontSize = 15.sp,
//                color = Color(0xFF2981FF),
//                modifier = Modifier.padding(start = 20.dp, end = 20.dp)
//            )
//        }
//
//    }
//
//}