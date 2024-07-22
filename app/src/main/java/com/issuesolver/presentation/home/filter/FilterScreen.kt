package com.issuesolver.presentation.home.filter

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.issuesolver.R
import com.issuesolver.domain.entity.networkModel.profile.UpdatePasswordRequest
import com.issuesolver.presentation.common.AuthButton
import com.issuesolver.presentation.home.home.HomeScreen
import com.issuesolver.presentation.newrequest.DropDownCategory
import com.issuesolver.presentation.profile.new_password.NewPasswordScreenEvent

@Composable
fun FilterScreen(){
    val suggestions = listOf("Kotlin", "Java", "Dart", "Python")


    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding()
            .padding(top = 22.dp, start = 20.dp, end = 20.dp, bottom = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
        ) {


            Text(
                "Filter",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 28.sp,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Start,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 20.dp)

            )



            Divider(
                thickness = 0.5.dp,
                color = Color(0xFF2981FF),
                modifier = Modifier.padding(end = 7.dp, bottom = 12.dp)
            )
            Column(

            ) {
                DropDownCategory(
                    category = "Problemin yönləndiriləcəyi qurum",
                    placeHolder = "Qurum",
                    list = suggestions
                )

                DropDownCategory(
                    category = "Problemin Kateqoriyası",
                    placeHolder = "Kateqoriya",
                    list = suggestions
                )
                DropDownCategory(
                    category = "Problemin statusu",
                    placeHolder = "Status",
                    list = suggestions
                )

                DropDownCategory(
                    category = "Problemin tarixi",
                    placeHolder = "Tarix",
                    list = suggestions
                )

            }


        }

        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            AuthButton(
                text = "Axtarış et",
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun FilterScreenPreview() {
    FilterScreen()
}
