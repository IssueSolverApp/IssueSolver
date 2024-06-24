package com.issuesolver.presentation.login.qeydiyyat_page

import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.issuesolver.R
import com.issuesolver.domain.entity.networkModel.RegisterRequestModel
import com.issuesolver.presentation.common.AuthButton
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class
)
@Composable
fun RegisterPage( viewModel: RegisterViewModel = hiltViewModel()) {


    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }


    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
//    var showPassword by remember { mutableStateOf(value = false) }
    var confirmShowPassword by remember { mutableStateOf(value = false) }

    var checked by remember { mutableStateOf(false) }

    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()



    Scaffold(content = { padding ->

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .navigationBarsPadding()
                    .imePadding()



                ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .imePadding()
                        .padding(top = 20.dp, start = 20.dp, end = 20.dp)

                ) {
                    Column {
                        Text(
                            "Qeydiyyat",
                            style = MaterialTheme.typography.headlineMedium,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Start,
                            color = Color.Black,


                            )

                        Text(
                            "Zəhmət olmasa, şəxsi məlumatlarınızı daxil edin.",
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Start,
                            color = Color(0xFF9D9D9D),
                            modifier = Modifier
                                .padding(top = 10.dp),


                            )
                    }


                    Divider(
                        modifier = Modifier
                            .padding(top = 20.dp),
                        thickness = 0.5.dp,
                        color = Color(0xFF2981FF)

                    )


                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                        //.imePadding()
                        //.imeNestedScroll()


//                        .bringIntoViewRequester(bringIntoViewRequester)
                    ) {
                        Column(modifier = Modifier.padding(top = 28.dp)) {
                            Text(
                                "Ad, soyad",
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 15.sp,
                            )
                            TextField(
                                shape = RoundedCornerShape(12.dp),
                                value = name,
                                onValueChange = {
                                    name = it
                                },
                                placeholder = {
                                    Text(
                                        ("Ad, soyad"),
                                        color = Color(0xFF9D9D9D)
                                    )
                                },
                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp),

                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color.White, // Background color of the TextField
                                    focusedIndicatorColor = Color.White, // Underline color when focused
                                    disabledTextColor = Color.Gray, // Text color when TextField is disabled
                                    errorCursorColor = Color.Red, // Cursor color when in error state
                                    cursorColor = Color.White // Cursor color
                                )

                            )
                        }
//                        Column(Modifier.padding(top = 20.dp)) {
//                            Text(
//                                "Soyad ",
//                                style = MaterialTheme.typography.bodySmall,
//                                fontSize = 15.sp,
//                            )
//                            TextField(
//                                shape = RoundedCornerShape(12.dp),
//                                value = surname,
//                                onValueChange = {
//                                    surname = it
//                                },
//                                placeholder = {
//                                    Text(
//                                        ("Soyad"),
//                                        color = Color(0xFF9D9D9D)
//                                    )
//                                },
//                                singleLine = true,
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(top = 10.dp),
//
//                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
//                                colors = TextFieldDefaults.textFieldColors(
//                                    containerColor = Color.White, // Background color of the TextField
//                                    focusedIndicatorColor = Color.White, // Underline color when focused
//                                    unfocusedIndicatorColor = Color.White, // Underline color when unfocused
//                                    disabledTextColor = Color.Gray, // Text color when TextField is disabled
//                                    errorIndicatorColor = Color.Red, // Underline color when in error state
//                                    errorCursorColor = Color.Red, // Cursor color when in error state
//                                    cursorColor = Color.White // Cursor color
//                                )
//
//                            )
//                        }


                        Column(Modifier.padding(top = 20.dp)) {
                            Text(
                                "E-poçt",
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 15.sp,
                            )
                            TextField(
                                shape = RoundedCornerShape(12.dp),
                                value = email,
                                onValueChange = {
                                    email = it
                                    emailError = false
                                },
                                isError = emailError,
                                placeholder = {
                                    Text(
                                        ("E-poçtunuzu daxil edin"),
                                        color = Color(0xFF9D9D9D)
                                    )
                                },
                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp),

                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color.White, // Background color of the TextField
                                    focusedIndicatorColor = Color.White, // Underline color when focused
                                    disabledTextColor = Color.Gray, // Text color when TextField is disabled
                                    errorCursorColor = Color.Red, // Cursor color when in error state
                                    cursorColor = Color.White
                                )

                            )
                        }


                        Column(Modifier.padding(top = 20.dp)) {
                            Text(
                                "Şifrə",
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 15.sp,
                            )
                            TextField(
                                shape = RoundedCornerShape(12.dp),
                                value = password,
                                onValueChange = { password = it },
                                placeholder = {
                                    Text(
                                        "Şifrənizi təyin edin",
                                        color = Color(0xFF9D9D9D)
                                    )
                                },
                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp),
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color.White, // Background color of the TextField
                                    focusedIndicatorColor = Color.White, // Underline color when focused
                                    disabledTextColor = Color.Gray, // Text color when TextField is disabled
                                    errorCursorColor = Color.Red, // Cursor color when in error state
                                    cursorColor = Color.White
                                ),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                //visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
//                                trailingIcon = {
//                                    val icon =
//                                        if (showPassword) painterResource(R.drawable.unhiddeneye) else painterResource(
//                                            R.drawable.hiddeneye
//                                        )
//                                    val description =
//                                        if (showPassword) "Hide password" else "Show password"
////                                    IconButton(onClick = { showPassword = !showPassword }) {
////                                        Icon(
////                                            painter = icon,
////                                            tint = Color(0xFF2981FF),
////                                            contentDescription = description
////                                        )
////                                    }
//                                }
                            )
                        }


                        Column(Modifier.padding(top = 20.dp)) {
                            Text(
                                "Şifrənin təsdiqi",
                                style = MaterialTheme.typography.bodySmall,
                                fontSize = 15.sp,
                            )
                            TextField(
                                shape = RoundedCornerShape(12.dp),
                                value = confirmPassword,
                                onValueChange = { confirmPassword = it },
                                placeholder = {
                                    Text(
                                        "Şifrənizi təsdiq edin",
                                        color = Color(0xFF9D9D9D)
                                    )
                                },
                                singleLine = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 10.dp),
                                colors = TextFieldDefaults.textFieldColors(
                                    containerColor = Color.White, // Background color of the TextField
                                    focusedIndicatorColor = Color.White, // Underline color when focused
                                    disabledTextColor = Color.Gray, // Text color when TextField is disabled
                                    errorCursorColor = Color.Red, // Cursor color when in error state
                                    cursorColor = Color.White // Cursor color
                                ),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                                visualTransformation = if (confirmShowPassword) VisualTransformation.None else PasswordVisualTransformation(),
                                trailingIcon = {
                                    val icon =
                                        if (confirmShowPassword) painterResource(R.drawable.unhiddeneye) else painterResource(
                                            R.drawable.hiddeneye
                                        )
                                    val description =
                                        if (confirmShowPassword) "Hide password" else "Show password"
                                    IconButton(onClick = {
                                        confirmShowPassword = !confirmShowPassword
                                    }) {
                                        Icon(
                                            painter = icon,
                                            tint = Color(0xFF2981FF),
                                            contentDescription = description
                                        )
                                    }
                                }
                            )
                        }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 23.dp, bottom = 110.dp)
                    ) {
                        Checkbox(modifier = Modifier
                            .background(Color.White)
                            .size(width = 24.dp, height = 24.dp),
                            checked = checked,
                            onCheckedChange = { checked = it }
                        )

                            Text(
                                modifier = Modifier.clickable {

                                },
                                text = "Istifadəçi şərtləri və Məxfilik siyasəti",
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 15.sp
                            )
                            Text(
                                " qəbul edirəm.",
                                color = Color(0xFF000B1B),
                                fontSize = 15.sp

                            )
                        }

                    }


                }
                //----------------------------------------------------------------------------------

                Column(
                    modifier = Modifier
//                        .imePadding()
                        .align(Alignment.BottomCenter)
                        .background(Color(0xFFF0F4F9))
                        .imePadding()

                ) {
                    AuthButton(
                        text = "Daxil ol",
                        onClick = {
                            val fullname = "$name $surname"
                            viewModel.register(
                                RegisterRequestModel(
                                    email = email,
                                    fullName = fullname,
                                    password = password,
                                    confirmPassword = confirmPassword
                                )
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 20.dp)
                    )


                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()

                    ) {
                        Text(
                            "Hesabınız varmı?",
                            color = Color(0xFF9D9D9D)
                        )
                        Text(
                            modifier = Modifier.clickable {

                            },
                            text = "Daxil olun",
                            color = MaterialTheme.colorScheme.primary
                        )

                    }
                }





            } // here





//---------------------------------------------------------------------------------------------------

//            //Box(Modifier.background(Color.Black)) {
//            Column(
//                modifier = Modifier
//                    .background(Color.Green)
//                    .imePadding()
//                    .fillMaxSize()
//                    .padding(top = 24.dp)
//                    .padding(20.dp)
//
//
//            ) {
//
//                Column(
//                    Modifier
//                        .background(Color.Yellow)
//                        .verticalScroll(rememberScrollState())
//                        .consumeWindowInsets(
//                            WindowInsets.systemBars.only(WindowInsetsSides.Vertical)
//                        )
//
//                ) {
//                    Column(
//                        Modifier.padding(bottom = 20.dp)
//
//                    ) {
//                        Text(
//                            "Daxil olun",
//                            style = MaterialTheme.typography.headlineMedium,
//                            fontSize = 28.sp,
//                            fontWeight = FontWeight.SemiBold,
//                            textAlign = TextAlign.Start,
//                            color = Color.Black,
//
//
//                            )
//
//                        Text(
//                            "Zəhmət olmasa, giriş üçün məlumatlarınızı daxil edin.",
//                            style = MaterialTheme.typography.bodySmall,
//                            fontSize = 15.sp,
//                            textAlign = TextAlign.Start,
//                            color = Color(0xFF9D9D9D),
//                            modifier = Modifier
//                                .padding(top = 10.dp),
//
//
//                            )
//                    }
//
//
//                    Spacer(modifier = Modifier.height(8.dp))
//
//                    Divider(
//                        thickness = 0.5.dp,
//                        color = Color(0xFF2981FF)
//
//                    )
//                    Spacer(modifier = Modifier.height(8.dp))
//
//
//
//
//
//                    Column(
//                        Modifier.padding(top = 20.dp)
//
//                    ) {
//                        Text(
//                            "Ad, Soyad ",
//                            style = MaterialTheme.typography.bodySmall,
//                            fontSize = 15.sp,
//                        )
//                        TextField(
//                            shape = RoundedCornerShape(12.dp),
//                            value = email,
//                            onValueChange = {
//                                email = it
//                                emailError = false
//                            },
//                            isError = emailError,
//                            placeholder = {
//                                Text(
//                                    ("Ad, Soyad"),
//                                    color = Color(0xFF9D9D9D)
//                                )
//                            },
//                            singleLine = true,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(top = 10.dp),
//
//                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
//                            colors = TextFieldDefaults.textFieldColors(
//                                containerColor = Color.White, // Background color of the TextField
//                                focusedIndicatorColor = Color.White, // Underline color when focused
//                                unfocusedIndicatorColor = Color.White, // Underline color when unfocused
//                                disabledTextColor = Color.Gray, // Text color when TextField is disabled
//                                errorIndicatorColor = Color.Red, // Underline color when in error state
//                                errorCursorColor = Color.Red, // Cursor color when in error state
//                                cursorColor = Color.White // Cursor color
//                            )
//
//                        )
//
//                        Spacer(modifier = Modifier.height(8.dp))
//
//                        Text(
//                            "E-poçt",
//                            style = MaterialTheme.typography.bodySmall,
//                            fontSize = 15.sp,
//                        )
//                        TextField(
//                            shape = RoundedCornerShape(12.dp),
//                            value = email,
//                            onValueChange = {
//                                email = it
//                                emailError = false
//                            },
//                            isError = emailError,
//                            placeholder = {
//                                Text(
//                                    ("E-poçtunuzu daxil edin"),
//                                    color = Color(0xFF9D9D9D)
//                                )
//                            },
//                            singleLine = true,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(top = 10.dp),
//
//                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
//                            colors = TextFieldDefaults.textFieldColors(
//                                containerColor = Color.White, // Background color of the TextField
//                                focusedIndicatorColor = Color.White, // Underline color when focused
//                                unfocusedIndicatorColor = Color.White, // Underline color when unfocused
//                                disabledTextColor = Color.Gray, // Text color when TextField is disabled
//                                errorIndicatorColor = Color.Red, // Underline color when in error state
//                                errorCursorColor = Color.Red, // Cursor color when in error state
//                                cursorColor = Color.White // Cursor color
//                            )
//
//                        )
//                        Spacer(modifier = Modifier.height(8.dp))
//
//                        Text(
//                            "Şifrə",
//                            style = MaterialTheme.typography.bodySmall,
//                            fontSize = 15.sp,
//                        )
//                        TextField(
//                            shape = RoundedCornerShape(12.dp),
//                            value = password,
//                            onValueChange = { password = it },
//                            placeholder = {
//                                Text(
//                                    "Şifrənizi təyin edin",
//                                    color = Color(0xFF9D9D9D)
//                                )
//                            },
//                            singleLine = true,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(top = 10.dp),
//                            colors = TextFieldDefaults.textFieldColors(
//                                containerColor = Color.White, // Background color of the TextField
//                                focusedIndicatorColor = Color.White, // Underline color when focused
//                                unfocusedIndicatorColor = Color.White, // Underline color when unfocused
//                                disabledTextColor = Color.Gray, // Text color when TextField is disabled
//                                errorIndicatorColor = Color.Red, // Underline color when in error state
//                                errorCursorColor = Color.Red, // Cursor color when in error state
//                                cursorColor = Color.White // Cursor color
//                            ),
//                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
//                            trailingIcon = {
//                                val icon =
//                                    if (showPassword) painterResource(R.drawable.unhiddeneye) else painterResource(
//                                        R.drawable.hiddeneye
//                                    )
//                                val description =
//                                    if (showPassword) "Hide password" else "Show password"
//                                IconButton(onClick = { showPassword = !showPassword }) {
//                                    Icon(
//                                        painter = icon,
//                                        tint = Color(0xFF2981FF),
//                                        contentDescription = description
//                                    )
//                                }
//                            }
//                        )
//
//                        Spacer(modifier = Modifier.height(8.dp))
//
//                        Text(
//                            "Şifrə",
//                            style = MaterialTheme.typography.bodySmall,
//                            fontSize = 15.sp,
//                        )
//                        TextField(
//                            shape = RoundedCornerShape(12.dp),
//                            value = password,
//                            onValueChange = { password = it },
//                            placeholder = {
//                                Text(
//                                    "Şifrənizi təyin edin",
//                                    color = Color(0xFF9D9D9D)
//                                )
//                            },
//                            singleLine = true,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(top = 10.dp),
//                            colors = TextFieldDefaults.textFieldColors(
//                                containerColor = Color.White, // Background color of the TextField
//                                focusedIndicatorColor = Color.White, // Underline color when focused
//                                unfocusedIndicatorColor = Color.White, // Underline color when unfocused
//                                disabledTextColor = Color.Gray, // Text color when TextField is disabled
//                                errorIndicatorColor = Color.Red, // Underline color when in error state
//                                errorCursorColor = Color.Red, // Cursor color when in error state
//                                cursorColor = Color.White // Cursor color
//                            ),
//                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
//                            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
//                            trailingIcon = {
//                                val icon =
//                                    if (showPassword) painterResource(R.drawable.unhiddeneye) else painterResource(
//                                        R.drawable.hiddeneye
//                                    )
//                                val description =
//                                    if (showPassword) "Hide password" else "Show password"
//                                IconButton(onClick = { showPassword = !showPassword }) {
//                                    Icon(
//                                        painter = icon,
//                                        tint = Color(0xFF2981FF),
//                                        contentDescription = description
//                                    )
//                                }
//                            }
//                        )
//
//
//
//                        Text(
//                            modifier = Modifier
//                                .clickable {}
//                                .fillMaxWidth()
//                                .wrapContentWidth(Alignment.End)
//                                .padding(top = 10.dp), text = "Şifrənizi unutmusunuz?",
//
//                            color = MaterialTheme.colorScheme.primary
//                        )
//
//
//                    }
//
//
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//
//                Box {
//                    Column(
//                        modifier = Modifier
//                            .align(Alignment.BottomCenter)
//                            .wrapContentHeight()
//                            .fillMaxWidth()
//                            .background(Color.Red),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        AuthButton(
//                            text = "Daxil ol",
//                            onClick = {},
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(bottom = 16.dp)
//                        )
//
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.Center,
//                            modifier = Modifier.fillMaxWidth()
//                        ) {
//                            Text(
//                                "Hesabınız varmı?",
//                                color = Color(0xFF9D9D9D)
//                            )
//                            Text(
//                                modifier = Modifier.clickable {
//
//                                },
//                                text = "Daxil olun",
//                                color = MaterialTheme.colorScheme.primary
//                            )
//
//                        }
//
//
//                    }
//                }
//
//            }
//
//
//            //}


    })

}
