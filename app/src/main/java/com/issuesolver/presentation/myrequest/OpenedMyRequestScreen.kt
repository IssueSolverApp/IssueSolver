package com.issuesolver.presentation.myrequest

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.issuesolver.R


@Composable
fun OpenedMyRequestScreen(navController:NavController){

    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .statusBarsPadding(),
        bottomBar = {
        },

        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
                    .padding(top = 15.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .imePadding()

                ) {
                    Column(
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

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            )
                        ) {

                            Column(
                                modifier = Modifier
                                    .wrapContentHeight()
                                    .padding(16.dp)
                            ) {

                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()

                                ) {

                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Image(
                                            painter = painterResource(id = R.drawable.et_profile_male), // Replace with your drawable resource
                                            contentDescription = "Profile Image",
                                            modifier = Modifier
                                                .size(32.dp)
                                        )

                                        Text(
                                            text = "Aynur Qəmbərova",
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = Color(0xFF2981FF),
                                            modifier = Modifier.padding(start = 6.dp),
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.W400
                                        )
                                    }

                                    Row(
                                        modifier = Modifier
                                            .clip(shape = CircleShape)
                                            .background(color = Color(0xFFc8dcfc))
                                            .padding(8.dp)
                                            .clickable(onClick = { }),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Image(
                                            painter = painterResource(R.drawable.ellipse_9),
                                            contentDescription = "ellipse_9",
                                            modifier = Modifier
                                                .padding(start = 20.dp)
                                                .size(8.dp)
                                        )
                                        Text(
                                            text = "Gözləmədə",
                                            color = Color(0xFF0169FE),
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.W400,
                                            modifier = Modifier.padding(start = 8.dp, end = 20.dp)
                                        )
                                    }


                                }

                                Text(
                                    text = "Daxili İşlər Nazirliyi",
                                    color = Color(0xFF002252),
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.W500,
                                    modifier = Modifier.padding(top = 8.dp, bottom = 39.dp)
                                )

                                Divider(
                                    thickness = 0.5.dp,
                                    color = Color(0xFFc3dcff),
                                    modifier = Modifier.padding(
                                        top = 8.dp,
                                        bottom = 16.dp
                                    )
                                )


                                Text(
                                    text = "Küçə heyvanlarına qarşı zorbalıq",
                                    color = Color(0xFF8C8C8C),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.W400,
                                    modifier = Modifier
                                        .clip(shape = RoundedCornerShape(50.dp))
                                        .background(color = Color(0xFFF0F4F9))
                                        .padding(
                                            top = 8.dp,
                                            bottom = 8.dp,
                                            start = 11.dp,
                                            end = 11.dp
                                        )
                                )


                                Text(
                                    text = "Office ipsum you must be muted. Teeth recap latest didn't at. Innovation hill as wider assassin heads-up stronger give. Who's cloud low out email later charts. Believe our territories good client incentivization decisions pole product with. Pushback like be reach incompetent. Need bake ditching another loss to. Algorithm now pants items future-proof needle elephant i'm synergize old. Optimize meat room dog board invested devil reach. Horse building more prioritize meat per stakeholders.\n" +
                                            "building",
                                    color = Color(0xFF6E6E6E),
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.W500,
                                    modifier = Modifier
                                        .padding(
                                            top = 8.dp,

                                        )
                                )
                                Row (
                                    modifier = Modifier.padding(top=16.dp, bottom = 12.dp)
                                ){
                                    Image(
                                        painter = painterResource(id = R.drawable.location_icon),
                                        contentDescription = "location_icon",
                                        modifier = Modifier
                                            .size(20.dp)
                                    )

                                    Text(
                                        text = "Lorem ipsum dolor sit amet, consectetur efficitur.",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color(0xFF2981FF),
                                        modifier = Modifier.padding(start = 6.dp),
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.W400
                                    )

                                }
                                Row {

                                    Image(
                                        painter = painterResource(id = R.drawable.calendar),
                                        contentDescription = "calendar",
                                        modifier = Modifier
                                            .size(20.dp)
                                    )

                                    Text(
                                        text = "01.08.2024, 14:30",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color(0xFF002252),
                                        modifier = Modifier.padding(start = 6.dp),
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.W400
                                    )
                                }




                                Divider(
                                    thickness = 0.5.dp,
                                    color = Color(0xFFc3dcff),
                                    modifier = Modifier.padding(
                                        top = 16.dp, bottom = 12.dp
                                    )
                                )

                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Row {
                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            IconButton(onClick = { }) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.heart_default),
                                                    contentDescription = null,
                                                    Modifier.size(20.dp)
                                                )
                                            }
                                            Text(
                                                text = "723",
                                                color = Color(0xFF002252),
                                                modifier = Modifier.padding(top = 4.dp),
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.W500
                                            )

                                        }

                                        Column(
                                            verticalArrangement = Arrangement.Center,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {

                                            IconButton(onClick = {  }) {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.coment),
                                                    contentDescription = null,
                                                    Modifier.size(20.dp)

                                                )
                                            }
                                            Text(
                                                text = "723",
                                                color = Color(0xFF002252),
                                                modifier = Modifier.padding(top = 4.dp),
                                                fontSize = 10.sp,
                                                fontWeight = FontWeight.W500
                                            )

                                        }

                                    }
                                    Row {
                                        IconButton(onClick = {  }) {
                                            Icon(
                                                painter = painterResource(id = R.drawable.vector),
                                                contentDescription = null,
                                            )
                                        }
                                    }
                                }

                            }

                        }


                    }

                }

            }
        }

    )
}
@Preview(showBackground = true)
@Composable
fun PreviewOpenedMyRequestScreen() {
    val navController = rememberNavController()
    OpenedMyRequestScreen(navController)
}

