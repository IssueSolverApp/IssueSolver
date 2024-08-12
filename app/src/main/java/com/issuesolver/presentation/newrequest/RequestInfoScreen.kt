package com.issuesolver.presentation.newrequest

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.issuesolver.R

@Composable
fun RequestInfoScreen(navController: NavController) {

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 15.dp)
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(Color.White)
                    .clickable {
                        navController.popBackStack()
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.backarray),
                    contentDescription = "Back",
                    modifier = Modifier.size(24.dp)
                )
            }

            Text(
                text = "Qaydalar",
                fontSize = 28.sp,
                color = Color(0xFF2981FF),
                modifier = Modifier.padding(top = 15.dp)
            )

            Divider(
                thickness = 0.5.dp,
                color = Color(0xFF2981FF),
                modifier = Modifier.padding(
                    top = 15.dp,
                    bottom = 9.dp
                )
            )

            Text(
                text = "Məlumatlar",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF000000),
            )


            Text(
                text = "Zəhmət olmasa, sorğunuzu aşağıdakı tələblərə uyğun şəkildə paylaşın:",
                fontSize = 17.sp,
                color = Color(0xFF000000),
                modifier = Modifier.padding(top = 20.dp)
                )


            Row {
                Text(
                    text = "•",
                    fontSize = 17.sp,
                    color = Color(0xFF000000),
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    text = "Nəzakətli bir dil istifadə edin.",
                    fontSize = 17.sp,
                    color = Color(0xFF000000),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row {
                Text(
                    text = "•",
                    fontSize = 17.sp,
                    color = Color(0xFF000000),
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    text = "Dəqiq yerləri qeyd edin.",
                    fontSize = 17.sp,
                    color = Color(0xFF000000),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row {
                Text(
                    text = "•",
                    fontSize = 17.sp,
                    color = Color(0xFF000000),
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    text = "Problemin nə qədər müddətdir davam etdiyini bildirin.",
                    fontSize = 17.sp,
                    color = Color(0xFF000000),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            Text(
                text = "Xəbərdarlıqlar",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF000000),
                modifier = Modifier.padding(top = 48.dp)
            )

            Text(
                text = "Zəhmət olmasa, sorğunuzda aşağıdakı məlumatları daxil etməyin:",
                fontSize = 17.sp,
                color = Color(0xFF000000),
                modifier = Modifier.padding(top = 20.dp)
            )


            Row {
                Text(
                    text = "•",
                    fontSize = 17.sp,
                    color = Color(0xFF000000),
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    text = "Şəxsi əlaqə məlumatları (telefon nömrəsi, e-poçt ünvanı, ev ünvanı və s.)",
                    fontSize = 17.sp,
                    color = Color(0xFF000000),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row {
                Text(
                    text = "•",
                    fontSize = 17.sp,
                    color = Color(0xFF000000),
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    text = "Başqalarını müəyyən edən məlumatlar (ad, soyad, FİN və s.)",
                    fontSize = 17.sp,
                    color = Color(0xFF000000),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row {
                Text(
                    text = "•",
                    fontSize = 17.sp,
                    color = Color(0xFF000000),
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    text = "Başqalarını ittiham edən ifadələr",
                    fontSize = 17.sp,
                    color = Color(0xFF000000),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }



        }
    }


}
