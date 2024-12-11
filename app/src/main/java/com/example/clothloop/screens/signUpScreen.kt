package com.example.clothloop.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.clothloop.R
import com.example.clothloop.viewModel.logInViewModel
import com.example.clothloop.viewModel.signUpViewModel
import kotlinx.coroutines.launch

@Composable
fun signUp(signUpViewModel: signUpViewModel = viewModel(), logInViewModel: logInViewModel = viewModel(), navController: NavHostController){
    val signUpStatus by signUpViewModel.isSignUpSuccess.collectAsState()
    val loading by signUpViewModel.isLoading.collectAsState()

    LaunchedEffect(signUpStatus){
        if (signUpStatus){
            navController.navigate("logIn")
        }
    }

    val coroutine = rememberCoroutineScope()
    Box(Modifier.fillMaxSize()) {
        backround()
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Box(
                Modifier
                    .width(290.dp)
                    .height(500.dp), contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "WELCOME TO CLOTHLOOP",
                        textAlign = TextAlign.Center,
                        color = Color(0XFF0A4635),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W900
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Create a new account",
                        textAlign = TextAlign.Center,
                        color = Color(0XFF0A4635),
                        fontSize = 16.sp,
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    textFieldBox(width = 280, desc = "Username", value = signUpViewModel.signUpState.value.name, onValueChange = { signUpViewModel.setName(it) })
                    if (signUpViewModel.signUpState.value.nameError.isNotEmpty()){
                        Box(
                            Modifier
                                .width(280.dp)
                                .height(25.dp), contentAlignment = Alignment.CenterStart) {
                            Text(text = signUpViewModel.signUpState.value.nameError, color = Color(0XFF0A4635), textAlign = TextAlign.Start, fontWeight = FontWeight.Medium)
                        }
                    } else {
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    textFieldBox(width = 280, desc = "Email", value = signUpViewModel.signUpState.value.email, onValueChange = { signUpViewModel.setEmail(it) })
                    if (signUpViewModel.signUpState.value.emailError.isNotEmpty()){
                        Box(
                            Modifier
                                .width(280.dp)
                                .height(25.dp), contentAlignment = Alignment.CenterStart) {
                            Text(text = signUpViewModel.signUpState.value.emailError, color = Color(0XFF0A4635), textAlign = TextAlign.Start, fontWeight = FontWeight.Medium)
                        }
                    } else {
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    TextFieldPasswordBox(width = 200, desc = "password", value = signUpViewModel.signUpState.value.password, onValueChange = { signUpViewModel.setPassword(it) })
                    if (signUpViewModel.signUpState.value.passwordError.isNotEmpty()){
                        Box(
                            Modifier
                                .width(280.dp)
                                .height(25.dp), contentAlignment = Alignment.CenterStart) {
                            Text(text = signUpViewModel.signUpState.value.passwordError, color = Color(0XFF0A4635), textAlign = TextAlign.Start, fontWeight = FontWeight.Medium)
                        }
                    } else {
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    TextFieldPasswordBox(width = 200, desc = "Confirm Password", value = signUpViewModel.signUpState.value.confirmPassword, onValueChange = { signUpViewModel.setConfirmPassword(it) })
                    if (signUpViewModel.signUpState.value.confirmPasswordError.isNotEmpty()){
                        Box(
                            Modifier
                                .width(280.dp)
                                .height(25.dp), contentAlignment = Alignment.CenterStart) {
                            Text(text = signUpViewModel.signUpState.value.confirmPasswordError, color = Color(0XFF0A4635), textAlign = TextAlign.Start, fontWeight = FontWeight.Medium)
                        }
                    } else {
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    textFieldNumberBox(width = 200, desc = "Phone Number", value = signUpViewModel.signUpState.value.phoneNumber, onValueChange = { signUpViewModel.setPhoneNumber(it) })
                    if (signUpViewModel.signUpState.value.phoneNumberError.isNotEmpty()){
                        Box(
                            Modifier
                                .width(280.dp)
                                .height(25.dp), contentAlignment = Alignment.CenterStart) {
                            Text(text = signUpViewModel.signUpState.value.phoneNumberError, color = Color(0XFF0A4635), textAlign = TextAlign.Start, fontWeight = FontWeight.Medium)
                        }
                    } else {
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                    if (loading){
                        Box(
                            Modifier
                                .width(130.dp)
                                .height(50.dp)
                                .background(
                                    color = Color(0XFF0A4635),
                                    shape = RoundedCornerShape(7.dp)
                                )
                            , contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(color = Color.White, strokeWidth = 5.dp, modifier = Modifier.size(25.dp))
                        }
                    } else {
                        Box(
                            Modifier
                                .width(130.dp)
                                .height(50.dp)
                                .background(
                                    color = Color(0XFF0A4635),
                                    shape = RoundedCornerShape(7.dp)
                                )
                                .clickable {
                                    coroutine.launch {
                                        if (signUpViewModel.validateAllFields()) {
                                            signUpViewModel.signUp(
                                                name = signUpViewModel.signUpState.value.name,
                                                email = signUpViewModel.signUpState.value.email,
                                                password = signUpViewModel.signUpState.value.confirmPassword,
                                                phoneNumber = signUpViewModel.signUpState.value.phoneNumber
                                            )
                                        } else {
                                            println("tetot")
                                        }
                                    }

                                }, contentAlignment = Alignment.Center
                        ) {
                            Text(text = "SignUp", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                    if(signUpViewModel.allFieldvalid.value == false) {
                        Box(
                            Modifier
                                .width(200.dp)
                                .height(35.dp)
                                .padding(vertical = 7.dp), contentAlignment = Alignment.Center) {
                            Text(text = "Please fill all fields correctly!", color =  Color(0XFF0A4635), fontWeight = FontWeight.Medium)
                        }
                    } else {
                        Box(modifier = Modifier)
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
                    Text(text = "Already have an account? ", color = Color(0XFF0A4635), fontSize = 16.sp)
                    Text(text = "LogIn", color = Color(0XFF0A4635), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
    }
}}}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textFieldBox(width: Int, desc: String, value: String, onValueChange: (String) -> Unit){
    Box(
        Modifier
            .width(280.dp)
            .height(50.dp)
            .clip(shape = RoundedCornerShape(7.dp))
            .background(color = Color.White, shape = RoundedCornerShape(7.dp))
            .border(width = 2.dp, color = Color(0XFF0A4635), shape = RoundedCornerShape(7.dp))
            .padding(vertical = 2.dp, horizontal = 10.dp)) {
        TextField(value = value, onValueChange = { onValueChange(it)},
            modifier = Modifier
                .width(width.dp)
                .height(70.dp), colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent, focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent
            ), textStyle = TextStyle(color = Color.Black, fontSize = 13.sp), placeholder = {
                Text(text = desc, color = Color.Gray, fontSize = 13.sp)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldPasswordBox(width: Int, desc: String, value: String, onValueChange: (String) -> Unit){


    var isPasswordVisible = remember { mutableStateOf(false) }

    Box(
        Modifier
            .width(280.dp)
            .height(50.dp)
            .clip(shape = RoundedCornerShape(7.dp))
            .background(color = Color.White, shape = RoundedCornerShape(7.dp))
            .border(width = 2.dp, color = Color(0XFF0A4635), shape = RoundedCornerShape(7.dp))
            .padding(vertical = 2.dp, horizontal = 10.dp)) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            TextField(value = value, onValueChange = { onValueChange(it)},
                visualTransformation = if (isPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier
                    .width(width.dp)
                    .height(70.dp), colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent, focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent
                ), textStyle = TextStyle(color = Color.Black, fontSize = 13.sp), placeholder = {
                    Text(text = desc, color = Color.Gray, fontSize = 13.sp)

                }
            )
            Spacer(modifier = Modifier.width(5.dp))
            IconButton(onClick = { isPasswordVisible.value = !isPasswordVisible.value }) {
                Icon(imageVector = if (isPasswordVisible.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff, contentDescription = "", tint = Color(0XFF0A4635))
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textFieldNumberBox(width: Int, desc: String, value: String, onValueChange: (String) -> Unit){
    Box(
        Modifier
            .width(280.dp)
            .height(50.dp)
            .clip(shape = RoundedCornerShape(7.dp))
            .background(color = Color.White, shape = RoundedCornerShape(7.dp))
            .border(width = 2.dp, color = Color(0XFF0A4635), shape = RoundedCornerShape(7.dp))
            .padding(vertical = 2.dp, horizontal = 10.dp)) {
        TextField(value = value, onValueChange = { onValueChange(it)},
            modifier = Modifier
                .width(width.dp)
                .height(70.dp), colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent, focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent
            ), textStyle = TextStyle(color = Color.Black, fontSize = 13.sp), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), placeholder = {
                Text(text = desc, color = Color.Gray, fontSize = 13.sp)
            }
        )
    }
}