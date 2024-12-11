package com.example.clothloop.screens

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.clothloop.viewModel.historyViewModel
import com.example.clothloop.viewModel.logInViewModel
import com.example.clothloop.viewModel.pickUpViewModel
import kotlinx.coroutines.launch

@Composable
fun history(navController: NavHostController, historyViewModel: historyViewModel = viewModel(), logInViewModel: logInViewModel = viewModel(), pickUpViewModel: pickUpViewModel = viewModel()) {
    val scrollState = rememberScrollState()
    val listOrder by historyViewModel.listOrder.collectAsState()
    val coroutine = rememberCoroutineScope()
    val fetchUserData by logInViewModel.fetchData.collectAsState()


    LaunchedEffect(Unit){
        historyViewModel.getAllOrder(logInViewModel.fetchData.value!!.email)
    }

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(color = Color(0XFFF2E8D8))
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(120.dp), contentAlignment = Alignment.BottomStart
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(PaddingValues(start = 20.dp, bottom = 20.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = "",
                    tint = Color(0XFF0A4635),
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { navController.navigate("home") }
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = "Your Balance & History",
                    fontWeight = FontWeight.W900,
                    color = Color(0XFF0A4635),
                    fontSize = 20.sp
                )
            }

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
                .background(color = Color.White, shape = RoundedCornerShape(5.dp))
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Your Balance:",
                        fontWeight = FontWeight.W900,
                        color = Color(0XFF0A4635),
                        fontSize = 20.sp
                    )
                    Row {
                        Text(text = "Rp", color = Color(0XFF0A4635), fontSize = 18.sp)
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = pickUpViewModel.convertWeightToRupiah(fetchUserData!!.totalWeightSelled), color = Color(0XFF0A4635), fontSize = 18.sp)
                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.fprofile),
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                        .clickable { println(listOrder) }
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "History:",
            fontWeight = FontWeight.W900,
            color = Color(0XFF0A4635),
            fontSize = 20.sp,
            modifier = Modifier.padding(horizontal = 30.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            Modifier
                .fillMaxWidth()
                .height(640.dp)) {
            if(listOrder.isEmpty()){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = "Oops, History is Still Empty!", color = Color(0XFF0A4635), fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }} else {
                LazyColumn() {
                    items(listOrder) { item ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 30.dp)
                                .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
                                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                        ) {
                            Column(
                                Modifier
                                    .padding(20.dp)
                            ) {
                                Text(
                                    text = item.userName,
                                    color = Color(0XFF0A4635),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(
                                    text = item.pickupdate,
                                    color = Color(0XFF0A4635),
                                    fontSize = 14.sp
                                )
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(
                                    text = item.category,
                                    color = Color(0XFF0A4635),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                Row {
                                    Text(
                                        text = "Weight: ",
                                        color = Color(0XFF0A4635),
                                        fontSize = 14.sp
                                    )
                                    Text(
                                        text = item.weight.toString(),
                                        color = Color(0XFF0A4635),
                                        fontSize = 14.sp
                                    )
                                    Text(
                                        text = " Kilogram",
                                        color = Color(0XFF0A4635),
                                        fontSize = 14.sp
                                    )
                                }
                                Row {
                                    Text(
                                        text = "Revenue: ",
                                        color = Color(0XFF0A4635),
                                        fontSize = 14.sp
                                    )
                                    Text(text = "Rp", color = Color(0XFF0A4635), fontSize = 14.sp)
                                    Text(
                                        text = " ${pickUpViewModel.convertWeightToRupiah(item.weight)}",
                                        color = Color(0XFF0A4635),
                                        fontSize = 14.sp
                                    )
                                }
                                Spacer(modifier = Modifier.height(15.dp))
                                Text(
                                    text = "Additional Notes:",
                                    color = Color(0XFF0A4635),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                Text(text = item.note, color = Color(0XFF0A4635), fontSize = 14.sp)
                                Spacer(modifier = Modifier.height(15.dp))
                                if (item.status == "") {
                                    Text(
                                        text = "Still in Process",
                                        fontWeight = FontWeight.W900,
                                        color = Color(0XFFFF0000),
                                        fontSize = 16.sp
                                    )
                                    Spacer(modifier = Modifier.height(15.dp))
                                    Text(
                                        text = "Has your wasted been collected?",
                                        fontWeight = FontWeight.W900,
                                        color = Color(0XFF0A4635),
                                        fontSize = 16.sp
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Row(Modifier.fillMaxWidth()) {
                                        Box(
                                            modifier = Modifier
                                                .height(40.dp)
                                                .clickable {
                                                    coroutine.launch {
                                                        historyViewModel.cancelOrder(item.orderId)
                                                    }
                                                }
                                                .weight(1f)
                                                .background(
                                                    color = Color(0XFFFF0000),
                                                    shape = RoundedCornerShape(20.dp)
                                                ), contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = "Cancel",
                                                color = Color.White,
                                                fontWeight = FontWeight.W900
                                            )
                                        }
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Box(
                                            modifier = Modifier
                                                .height(40.dp)
                                                .clickable {
                                                    coroutine.launch {
                                                        historyViewModel.completeOrder(
                                                            uid = item.orderId,
                                                            email = logInViewModel.fetchData.value!!.email,
                                                            weight = item.weight
                                                        )
                                                    }
                                                }
                                                .weight(1f)
                                                .background(
                                                    color = Color(0XFF0A4635),
                                                    shape = RoundedCornerShape(20.dp)
                                                ), contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = "Mark As Complete",
                                                color = Color.White,
                                                fontWeight = FontWeight.W900
                                            )
                                        }
                                    }
                                } else {
                                    if (item.status == "cancelled") {
                                        Text(
                                            text = "cancelled",
                                            fontWeight = FontWeight.W900,
                                            color = Color.Gray,
                                            fontSize = 16.sp
                                        )
                                    } else {
                                        Text(
                                            text = "completed",
                                            fontWeight = FontWeight.W900,
                                            color = Color(0XFF37FF00),
                                            fontSize = 16.sp
                                        )
                                    }
                                }

                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}