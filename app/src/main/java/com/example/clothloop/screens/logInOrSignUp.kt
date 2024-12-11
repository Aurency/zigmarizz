package com.example.clothloop.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.clothloop.R

@Composable
fun logInOrSignUp() {
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
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "",
                        Modifier.size(150.dp)
                    )
                    Text(
                        text = "Transform waste into sustainable style. With ClothLoop, recycling is effortless and fashionable!",
                        textAlign = TextAlign.Center,
                        color = Color(0XFF0A4635),
                        fontSize = 18.sp
                    )
                    Box(
                        Modifier
                            .height(220.dp)
                            .width(280.dp)
                            , contentAlignment = Alignment.Center,
                    ) {
                        Column(Modifier.align(Alignment.Center)) {
                            Box(
                                Modifier
                                    .width(220.dp)
                                    .clickable {
                                    }
                                    .height(50.dp)
                                    .border(
                                        width = 2.dp,
                                        color = Color(0XFF0A4635),
                                        shape = RoundedCornerShape(10.dp)
                                    ),
                                contentAlignment = Alignment.Center) {
                                Text(
                                    text = "logIn",
                                    color = Color(0XFF0A4635),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            }
                            Spacer(modifier = Modifier.height(7.dp))
                            Box(
                                Modifier
                                    .width(220.dp)
                                    .clickable {
                                    }
                                    .height(50.dp)
                                    .background(
                                        Color(0XFF0A4635),
                                        shape = RoundedCornerShape(10.dp)
                                    ), contentAlignment = Alignment.Center) {
                                Text(
                                    text = "SignUp",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}