package com.issuesolver.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.issuesolver.R

@Composable
fun PopUp(
    text: String,
    button1: String,
    button2: String,
    onConfirmation: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(8.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = text,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = W400,
                    textAlign = TextAlign.Center

                )
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top=48.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = { onConfirmation( ) }) {
                        Text(
                            text = button1,
                            fontSize = 20.sp,
                            fontWeight = W400,
                            color = Color(0xFFEF5648),

                            )
                    }
                    Image(
                        painter = painterResource(R.drawable.line),
                        contentDescription = "line",
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .height(24.dp)
                    )
                    TextButton(onClick = { onDismiss() }) {
                        Text(
                            text = button2,
                            fontSize = 20.sp,
                            fontWeight = W400,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPopUp() {
    PopUp(
        text = "Hesabınızı silmək istədiyinizə əminsiniz?",
        button1 = "Bəli",
        button2 = "Xeyr",
        onConfirmation = {},
        onDismiss = {}
    )
}
