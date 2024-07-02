//package com.issuesolver.presentation.common
//
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.AlertDialog
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun AlertDialogExample(
//    onConfirmation: () -> Unit,
//    ) {
//    AlertDialog(
//        modifier = Modifier
//            .width(249.dp)
//            .height(176.dp)
//            .padding(16.dp)
//            .clip(RoundedCornerShape(8.dp)),
//        title = {
//            Text(
//                "Həddən artıq uğursuz cəhd, bir müddət sonra yenidən yoxlayın",
//                color = Color(0xFF4D96FF),
//                fontSize = 20.sp,
//                textAlign = TextAlign.Center,
//            )
//        },
//        onDismissRequest = {},
//        confirmButton = {
//            TextButton(
//                onClick = {
//                    onConfirmation()
//                }
//            ) {
//                Text(
//                    "Oldu",
//                    color = Color.Black,
//                    fontSize = 20.sp,
//                    textAlign = TextAlign.Center,
//                )
//            }
//        }
//    )
//}
//
//
//
