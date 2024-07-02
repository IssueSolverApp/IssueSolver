package com.issuesolver.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.issuesolver.R

@Composable
fun AuthButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false
) {
    val buttonColor = Color(0xFF2981FF)
    Button(
        onClick = { onClick() },
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp),
        shape = RoundedCornerShape(50.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            disabledContainerColor = Color(0xFF9AC2FB)
        )
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.W500,
            fontSize = 18.sp,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewAuthButton() {
    AuthButton(
        text = "Təsdiq kodu göndər",
        onClick = {}
    )
}
