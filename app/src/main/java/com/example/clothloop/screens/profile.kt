package com.example.clothloop.screens

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.clothloop.R
import com.example.clothloop.viewModel.logInViewModel
import com.example.clothloop.viewModel.pickUpViewModel
import com.example.clothloop.viewModel.signUpViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate

@Composable
fun profile(signUpViewModel: signUpViewModel = viewModel(), logInViewModel: logInViewModel = viewModel(), navController: NavHostController, pickUpViewModel: pickUpViewModel = viewModel()) {
    var isEdit by remember {
        mutableStateOf(false)
    }
    val today = LocalDate.now()
    val year = today.year
    val month = today.monthValue - 1
    val day = today.dayOfMonth
    val context = LocalContext.current
    val data by logInViewModel.fetchData.collectAsState()
    val coroutine = rememberCoroutineScope()


    val datePickerDialog = remember {
        DatePickerDialog(context as Context, { _, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selectedDate = LocalDate.of(selectedYear, selectedMonth + 1, selectedDayOfMonth)
            signUpViewModel.newBirthDate(selectedDate)
            Log.d("Selected Date", selectedDate.toString())
        }, year, month, day)
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(color = Color(0XFFF2E8D8))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.llipse2),
                contentDescription = "",
                modifier = Modifier.fillMaxSize()
            )
        }
        Column(
            Modifier
                .fillMaxWidth()
                .padding(PaddingValues(start = 30.dp, end = 30.dp, top = 100.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Profile",
                fontWeight = FontWeight.Bold,
                color = Color(0XFFF2E8D8),
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.fprofile),
                contentDescription = "",
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 5.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 30.dp, horizontal = 20.dp)
                ) {
                    Text(
                        text = "Full Name",
                        color = Color(0XFF0A4635),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    if (isEdit) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp), contentAlignment = Alignment.CenterStart
                        ) {
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                textFieldEditBox(
                                    width = 400,
                                    desc = logInViewModel.fetchData.value.name,
                                    value = signUpViewModel.signUpState.value.name,
                                    onValueChange = { signUpViewModel.setName(it) }
                                )
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp), contentAlignment = Alignment.CenterStart
                        ) {
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = data.name, color = Color.Gray)
                                Icon(imageVector = Icons.Filled.Edit,
                                    contentDescription = "",
                                    tint = Color(0XFF0A4635),
                                    modifier = Modifier
                                        .size(25.dp)
                                        .clickable { isEdit = true })
                            }
                        }
                    }
                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 2.dp,
                        color = Color(0XFF0A4635)
                    )
                    Spacer(modifier = Modifier.height(7.dp))
                    Text(
                        text = "Birth Date",
                        color = Color(0XFF0A4635),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    if (isEdit) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                        ) {
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = pickUpViewModel.pickUpState.value.date.toString(),
                                    color = Color.Gray
                                )
                                Box(
                                    Modifier
                                        .height(25.dp)
                                        .clickable {
                                            datePickerDialog.show()
                                        }
                                        .width(50.dp)
                                        .border(
                                            width = 2.dp,
                                            color = Color(0XFF0A4635),
                                            shape = RoundedCornerShape(7.dp)
                                        ), contentAlignment = Alignment.Center) {
                                    Text(text = "Select", color = Color(0XFF0A4635))
                                }
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp), contentAlignment = Alignment.CenterStart
                        ) {
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = data.birthDate, color = Color.Gray)
                                Icon(imageVector = Icons.Filled.Edit,
                                    contentDescription = "",
                                    tint = Color(0XFF0A4635),
                                    modifier = Modifier
                                        .size(25.dp)
                                        .clickable { isEdit = true })
                            }

                        }
                    }

                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 2.dp,
                        color = Color(0XFF0A4635)
                    )
                    Spacer(modifier = Modifier.height(7.dp))
                    Text(
                        text = "Phone Number",
                        color = Color(0XFF0A4635),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    if (isEdit) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp), contentAlignment = Alignment.CenterStart
                        ) {
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                textFieldEditNumberBox(
                                    width = 400,
                                    desc = logInViewModel.fetchData.value.phoneNumber,
                                    value = signUpViewModel.signUpState.value.phoneNumber,
                                    onValueChange = { signUpViewModel.setPhoneNumber(it) }
                                )
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp), contentAlignment = Alignment.CenterStart
                        ) {
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = data.phoneNumber, color = Color.Gray)
                                Icon(imageVector = Icons.Filled.Edit,
                                    contentDescription = "",
                                    tint = Color(0XFF0A4635),
                                    modifier = Modifier
                                        .size(25.dp)
                                        .clickable { isEdit = true })
                            }
                        }
                    }
                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 2.dp,
                        color = Color(0XFF0A4635)
                    )
                    Spacer(modifier = Modifier.height(7.dp))
                    Text(
                        text = "Address",
                        color = Color(0XFF0A4635),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    if (isEdit) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp), contentAlignment = Alignment.CenterStart
                        ) {
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                textFieldEditBox(
                                    width = 400,
                                    desc = logInViewModel.fetchData.value.address,
                                    value = signUpViewModel.signUpState.value.newAddress,
                                    onValueChange = { signUpViewModel.setNewAddress(it) }
                                )
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp), contentAlignment = Alignment.CenterStart
                        ) {
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = data.address, color = Color.Gray)
                                Icon(imageVector = Icons.Filled.Edit,
                                    contentDescription = "",
                                    tint = Color(0XFF0A4635),
                                    modifier = Modifier
                                        .size(25.dp)
                                        .clickable { isEdit = true })
                            }
                        }
                    }
                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 2.dp,
                        color = Color(0XFF0A4635)
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            if (isEdit) {
                Box(
                    Modifier
                        .width(250.dp)
                        .height(55.dp)
                        .clickable {
                            coroutine.launch {
                                logInViewModel.editData(
                                    email = data.email,
                                    name = if (signUpViewModel.signUpState.value.name == "") data.name else signUpViewModel.signUpState.value.name,
                                    birthDate = signUpViewModel.signUpState.value.birthDate.toString(),
                                    phoneNumber = if (signUpViewModel.signUpState.value.phoneNumber == "") data.phoneNumber else signUpViewModel.signUpState.value.phoneNumber,
                                    address = if (signUpViewModel.signUpState.value.newAddress == "") data.address else signUpViewModel.signUpState.value.newAddress
                                )
                                isEdit = false
                            }
                        }
                        .background(color = Color(0XFF0A4635), shape = RoundedCornerShape(40.dp)),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = "Save Changes",
                        color = Color(0XFFF2E8D8),
                        fontWeight = FontWeight.W900,
                        fontSize = 20.sp
                    )
                }
            } else {
                Box(Modifier.shadow(elevation = 5.dp, shape = RoundedCornerShape(20.dp)).background(color = Color.Red, shape = RoundedCornerShape(20.dp)).clickable { logInViewModel.logout() }, contentAlignment = Alignment.Center){
                    Text(text = "Log Out", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.padding(horizontal = 12.dp, vertical = 9.dp))
                }

            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textFieldEditBox(width: Int, desc: String, value: String, onValueChange: (String) -> Unit){
        TextField(value = value, onValueChange = { onValueChange(it)},
            modifier = Modifier
                .width(width.dp)
                .height(70.dp), colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent, focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent
            ), textStyle = TextStyle(color = Color.Black, fontSize = 13.sp), placeholder = {
                Text(text = desc, color = Color.Gray, fontSize = 13.sp)
            }
        )
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textFieldEditNumberBox(width: Int, desc: String, value: String, onValueChange: (String) -> Unit){
    TextField(value = value, onValueChange = { onValueChange(it)},
        modifier = Modifier
            .width(width.dp)
            .height(70.dp), colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent, focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent
        ), textStyle = TextStyle(color = Color.Black, fontSize = 13.sp), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), placeholder = {
            Text(text = desc, color = Color.Gray, fontSize = 13.sp)
        }
    )
}