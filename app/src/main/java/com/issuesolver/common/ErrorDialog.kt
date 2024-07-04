package com.issuesolver.common


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AlertDialogExample(
    message: String,
    onConfirmation: () -> Unit,

    ) {

    AlertDialog(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp)),
        title = {
            Text(
                text = message,
                color = Color.Black,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
            )
        },
        onDismissRequest = {},
        confirmButton = {
            TextButton(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(
                    "Oldu",
                    color = Color(0xFF4D96FF),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAlertDialogExample() {
    AlertDialogExample(
        message = "Həddən artıq uğursuz cəhd, bir müddət sonra yenidən yoxlayın",
        onConfirmation = {},

    )
}
