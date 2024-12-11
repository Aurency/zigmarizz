package com.example.clothloop.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.clothloop.R
import com.example.clothloop.viewModel.logInViewModel

@Composable
fun home(logInViewModel: logInViewModel = viewModel(), navController: NavHostController){
    val scrollState = rememberScrollState()
    val userData by logInViewModel.fetchData.collectAsState()

    BackHandler {

    }
    Box(
        Modifier
            .fillMaxSize()
            .background(color = Color(0XFFF2E8D8))) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)){
            Image(painter = painterResource(id = R.drawable.llipse2), contentDescription = "", modifier = Modifier.fillMaxSize())
        }
            Column(
                Modifier
                    .padding(PaddingValues(start = 20.dp, end = 20.dp))
                    .fillMaxSize()
                    .verticalScroll(scrollState)) {
                Spacer(modifier = Modifier.height(80.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text(text = "Welcome,", color = Color(0XffF2E8D8), fontSize = 18.sp)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = userData.name, color = Color(0XffF2E8D8), fontSize = 22.sp, fontWeight = FontWeight.W900)
                    }
                    Box(
                        Modifier
                            .size(80.dp)
                            .background(color = Color.Transparent)
                            .clickable { navController.navigate("profile") }, contentAlignment = Alignment.Center) {
                        Image(painter = painterResource(id = R.drawable.fprofile), contentDescription = "", modifier = Modifier.size(70.dp))
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(
                            color = Color.White.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(30.dp)
                        ), contentAlignment = Alignment.CenterStart) {
                    Row(Modifier.padding(horizontal = 20.dp, vertical = 5.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Filled.LocationOn, contentDescription = "", tint = Color(0XFFF2E8D8), modifier = Modifier.size(30.dp))
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = if(userData.address == "") "" else userData.address, color = Color(0XFFF2E8D8), fontSize = 18.sp, fontWeight = FontWeight.Medium)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .shadow(elevation = 5.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))){
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(20.dp)) {
                        Box(modifier = Modifier
                            .fillMaxHeight()
                            .width(200.dp)
){
                            Column(Modifier.fillMaxWidth()) {
                                Text(text = "Exchange your textile waste now!", fontWeight = FontWeight.W900, fontSize = 20.sp, color = Color(0XFF0A4635))
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(text = "Clothloop merupakan aplikasi \n" +
                                        "yang memudahkan pengguna mendaur ulang limbah tekstil dengan menukarkan pakaian dan kain bekas untuk diproses menjadi produk baru, mendukung gaya hidup berkelanjutan dan ramah lingkungan.", color = Color(0XFF0A4635))
                            }

                        }
                        Box(modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(), contentAlignment = Alignment.Center){
                            Image(painter = painterResource(id = R.drawable.garbage), contentDescription = "", modifier = Modifier.size(150.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Category Menu", fontSize = 22.sp, fontWeight = FontWeight.W900, color = Color(0XFF0A4635))
                Spacer(modifier = Modifier.height(10.dp))
                Row(Modifier.fillMaxWidth()) {
                    Box(modifier = Modifier
                        .height(160.dp)
                        .clickable {
                            navController.navigate("pickUp")
                        }
                        .weight(1f)
                        .shadow(elevation = 5.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(10.dp))){
                        Column(
                            Modifier
                                .fillMaxSize()
                                .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(painter = painterResource(id = R.drawable.truck), contentDescription = "", modifier = Modifier.size(80.dp))
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Textile Waste Pickup", color = Color(0XFF0A4635))
                            Spacer(modifier = Modifier.height(5.dp))
                            Divider(modifier = Modifier.fillMaxWidth(), thickness = 2.dp, color = Color(0XFF0A4635))
                        }
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Box(modifier = Modifier
                        .height(160.dp)
                        .clickable {
                            navController.navigate("types")
                        }
                        .weight(1f)
                        .shadow(elevation = 5.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(10.dp))){
                        Column(
                            Modifier
                                .fillMaxSize()
                                .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(painter = painterResource(id = R.drawable.blankie), contentDescription = "", modifier = Modifier.size(80.dp))
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Types of Textile Waste", color = Color(0XFF0A4635))
                            Spacer(modifier = Modifier.height(5.dp))
                            Divider(modifier = Modifier.fillMaxWidth(), thickness = 2.dp, color = Color(0XFF0A4635))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .shadow(elevation = 5.dp)
                    .clickable {
                        navController.navigate("history")
                    }
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))){
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(modifier = Modifier.size(100.dp), contentAlignment = Alignment.Center){
                            Image(painter = painterResource(id = R.drawable.balance), contentDescription = "", modifier = Modifier.size(80.dp))
                            Image(painter = painterResource(id = R.drawable.history), contentDescription = "", modifier = Modifier
                                .size(50.dp)
                                .offset(x = -5.dp, y = -25.dp))
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Balance & History", color = Color(0XFF0A4635))
                        Spacer(modifier = Modifier.height(7.dp))
                        Divider(modifier = Modifier.fillMaxWidth(), thickness = 2.dp, color = Color(0XFF0A4635))
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }

//