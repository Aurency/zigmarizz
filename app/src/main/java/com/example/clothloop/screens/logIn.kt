package com.example.clothloop.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clothloop.R
import com.example.clothloop.viewModel.signUpViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.clothloop.viewModel.logInViewModel
import kotlinx.coroutines.launch

@Composable
fun logIn(signUpViewModel: signUpViewModel = viewModel(), logInViewModel: logInViewModel = viewModel(), navController: NavHostController) {
    val coroutine = rememberCoroutineScope()
    val isLoggedin by logInViewModel.isLoggedin.collectAsState()
    val loading by logInViewModel.isLoading.collectAsState()

    LaunchedEffect(isLoggedin){
        if (isLoggedin){
            navController.navigate("home")
        }
    }

    Box(Modifier.fillMaxSize()) {
        backround()
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Box(
                Modifier
                    .width(290.dp)
                    .height(500.dp), contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "WELCOME BACK!",
                        textAlign = TextAlign.Center,
                        color = Color(0XFF0A4635),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Image(
                        painter = painterResource(id = R.drawable.safe),
                        contentDescription = "",
                        Modifier.size(150.dp)
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    textFieldBox(
                        width = 280,
                        desc = "Email",
                        value = signUpViewModel.signUpState.value.email,
                        onValueChange = { signUpViewModel.setEmail(it) })
                    if (signUpViewModel.signUpState.value.emailError.isNotEmpty()) {
                        Box(
                            Modifier
                                .width(280.dp)
                                .height(25.dp), contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = signUpViewModel.signUpState.value.emailError,
                                color = Color(0XFF0A4635),
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    TextFieldPasswordBox(
                        width = 200,
                        desc = "password",
                        value = signUpViewModel.signUpState.value.password,
                        onValueChange = { signUpViewModel.setPassword(it) }
                    )
                    if (signUpViewModel.signUpState.value.passwordError.isNotEmpty()) {
                        Box(
                            Modifier
                                .width(280.dp)
                                .height(25.dp), contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = signUpViewModel.signUpState.value.passwordError,
                                color = Color(0XFF0A4635),
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    if (loading) {
                        Box(
                            Modifier
                                .width(130.dp)
                                .height(50.dp)
                                .background(
                                    color = Color(0XFF0A4635),
                                    shape = RoundedCornerShape(7.dp)
                                ), contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                color = Color.White,
                                strokeWidth = 5.dp,
                                modifier = Modifier.size(25.dp)
                            )
                        }
                    } else {
                        Box(
                            Modifier
                                .width(130.dp)
                                .clickable {
                                    coroutine.launch {
                                        logInViewModel.logIn(
                                            email = signUpViewModel.signUpState.value.email,
                                            password = signUpViewModel.signUpState.value.password
                                        )
                                    }

                                }
                                .height(50.dp)
                                .background(
                                    color = Color(0XFF0A4635),
                                    shape = RoundedCornerShape(7.dp)
                                ), contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Login", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .align(Alignment.BottomCenter), contentAlignment = Alignment.BottomCenter) {
            Column(
            ) {
                Row(Modifier.padding(bottom = 20.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Dont have an account? ", color = Color(0XFF0A4635), fontSize = 16.sp)
                    Text(text = "Sign Up", color = Color(0XFF0A4635), fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.clickable {
                        navController.navigate("signUp")
                    })
                }
            }
        }
    }
}

