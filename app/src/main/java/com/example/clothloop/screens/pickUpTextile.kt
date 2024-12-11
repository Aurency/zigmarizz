package com.example.clothloop.screens

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.clothloop.R
import com.example.clothloop.data.model.pickUpTextileModel
import com.example.clothloop.viewModel.logInViewModel
import com.example.clothloop.viewModel.pickUpViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun pickUpTextile(pickUpViewModel: pickUpViewModel = viewModel(), logInViewModel: logInViewModel = viewModel(), navController: NavHostController) {
    val scrollState = rememberScrollState()
    var showDropdown by remember { mutableStateOf(false) }
    var dropdownOffset by remember { mutableStateOf(Offset(0f, 0f)) }
    var dropdownWidth by remember { mutableStateOf(0) }
    var optionSelected by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val selectedDate = remember { mutableStateOf("No Date Selected") }
    val today = LocalDate.now()
    val year = today.year
    val month = today.monthValue - 1
    val day = today.dayOfMonth
    val coroutine = rememberCoroutineScope()
    val isSuccess by pickUpViewModel.isSuccess.collectAsState()
    val loading by pickUpViewModel.isLoading.collectAsState()

    LaunchedEffect(isSuccess){
        if(isSuccess){
            navController.navigate("home")
            pickUpViewModel.setBackToFalse(false)
        }
    }

    val datePickerDialog = remember {
        DatePickerDialog(context as Context, { _, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selectedDate = LocalDate.of(selectedYear, selectedMonth + 1, selectedDayOfMonth)
            pickUpViewModel.setPickUpDate(selectedDate)
            Log.d("Selected Date", selectedDate.toString())
        }, year, month, day)
    }




    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color(0XFFF2E8D8))
            .verticalScroll(scrollState),
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
                    text = "Textile Waste Pickup",
                    fontWeight = FontWeight.W900,
                    color = Color(0XFF0A4635),
                    fontSize = 20.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 5.dp, shape = RoundedCornerShape(10.dp))
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp))
            ) {
                Column {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(80.dp)
                            .background(
                                color = Color(0XFF0A4635),
                                shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                            )
                    ) {
                        Row(
                            Modifier.padding(
                                PaddingValues(
                                    start = 20.dp,
                                    top = 20.dp,
                                    bottom = 20.dp
                                )
                            ), verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.info),
                                contentDescription = "",
                                modifier = Modifier.size(40.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Please fill in the data below correctly!",
                                color = Color(0XFFF2E8D8),
                                fontSize = 14.sp
                            )
                        }
                    }
                    Column(Modifier.padding(horizontal = 20.dp, vertical = 30.dp)) {
                        Text(
                            text = "Full Name",
                            color = Color(0XFF0A4635),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                               ) {
                            TextField(value = pickUpViewModel.pickUpState.value.username, onValueChange = { pickUpViewModel.setUsername(it) },
                                modifier = Modifier
                                    .fillMaxSize(), colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent, focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent
                                ), textStyle = TextStyle(color = Color.Black, fontSize = 16.sp), placeholder = {
                                    Text(text = "", color = Color.Gray, fontSize = 13.sp)
                                })
                        }
                        Divider(Modifier.fillMaxWidth(), thickness = 2.dp, Color(0XFF0A4635))
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "Waste Category",
                            color = Color(0XFF0A4635),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                                .border(
                                    width = 2.dp,
                                    color = Color(0XFF0A4635),
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .onGloballyPositioned { coordinates ->
                                    val position = coordinates.positionInWindow()
                                    dropdownOffset =
                                        Offset(position.x, position.y + coordinates.size.height)
                                    dropdownWidth = coordinates.size.width
                                }
                                .background(
                                    color = Color.Transparent,
                                    shape = RoundedCornerShape(10.dp)
                                )
                                .clickable { showDropdown = !showDropdown }, contentAlignment = Alignment.Center) {
                                Row(
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                    Text(text = if(optionSelected == "") "Choose Your Category" else optionSelected, color =  if(optionSelected == "") Color.Gray else Color.Black)
                                    Icon(
                                        imageVector = Icons.Filled.KeyboardArrowDown,
                                        contentDescription = "",
                                    )
                                }
                            
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(Modifier.fillMaxWidth()) {
                            Column(Modifier.weight(1f)) {
                                Text(text = "Weight/Kilogram", color =  Color(0XFF0A4635), fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .height(40.dp)
                                        , contentAlignment = Alignment.Center) {
                                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                                        if(pickUpViewModel.pickUpState.value.weight == 1){
                                            Box(modifier = Modifier)
                                        } else {
                                            Icon(imageVector = Icons.Filled.Remove, contentDescription = "", tint = Color.Black, modifier = Modifier
                                                .size(20.dp)
                                                .clickable { pickUpViewModel.minWeight() })

                                        }
                                        Text(text = pickUpViewModel.pickUpState.value.weight.toString(), color = Color.Black, fontSize = 16.sp)
                                        Icon(imageVector = Icons.Filled.Add, contentDescription = "", tint = Color.Black, modifier = Modifier
                                            .size(20.dp)
                                            .clickable { pickUpViewModel.addWeight() })
                                    }
                                    
                                }
                                Divider(Modifier.fillMaxWidth(), thickness = 2.dp, Color(0XFF0A4635))
                            }
                            Spacer(modifier = Modifier.width(20.dp))
                            Column(Modifier.weight(1f)) {
                                Text(text = "Price/Kilogram", color =  Color(0XFF0A4635), fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .height(40.dp),
                                         contentAlignment = Alignment.CenterStart) {
                                    Row(
                                        Modifier
                                            .fillMaxWidth()
                                            .padding(PaddingValues(start = 10.dp)), verticalAlignment = Alignment.CenterVertically) {
                                        Text(text = "Rp", color = Color(0XFF0A4635))
                                        Spacer(modifier = Modifier.width(5.dp))
                                        Text(text = pickUpViewModel.convertWeightToRupiah(pickUpViewModel.pickUpState.value.weight), color = Color(0XFF0A4635))
                                    }
                                }
                                Divider(Modifier.fillMaxWidth(), thickness = 2.dp, Color(0XFF0A4635))
                            }
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "pickup Date",
                            color = Color(0XFF0A4635),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(40.dp), contentAlignment = Alignment.Center) {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 5.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Text(text = pickUpViewModel.pickUpState.value.date.toString(), color = Color.Black)
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
                        Divider(Modifier.fillMaxWidth(), thickness = 2.dp, Color(0XFF0A4635))
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "Address",
                            color = Color(0XFF0A4635),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(50.dp))
                                 {
                                     TextField(value = pickUpViewModel.pickUpState.value.address, onValueChange = { pickUpViewModel.setAddresse(it) },
                                         modifier = Modifier
                                             .fillMaxSize(), colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent, focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent
                                         ), textStyle = TextStyle(color = Color.Black, fontSize = 16.sp), placeholder = {
                                             Text(text = "", color = Color.Gray, fontSize = 13.sp)
                                         })
                        }
                        Divider(Modifier.fillMaxWidth(), thickness = 2.dp, Color(0XFF0A4635))
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "Actived Phone Number",
                            color = Color(0XFF0A4635),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(50.dp))
                        {
                            TextField(value = pickUpViewModel.pickUpState.value.activedPhoneNumber, onValueChange = { pickUpViewModel.setActivedPhoneNumber(it) },
                                modifier = Modifier
                                    .fillMaxSize(), colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent, focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent
                                ), textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), placeholder = {
                                    Text(text = "", color = Color.Gray, fontSize = 13.sp)
                                })
                        }
                        Divider(Modifier.fillMaxWidth(), thickness = 2.dp, Color(0XFF0A4635))
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "Additional Notes (Optional)",
                            color = Color(0XFF0A4635),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            TextField(value = pickUpViewModel.pickUpState.value.additionalNotes, onValueChange = { pickUpViewModel.setAdditionalNotes(it) },
                                modifier = Modifier
                                    .fillMaxSize(), colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent, focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent
                                ), textStyle = TextStyle(color = Color.Black, fontSize = 16.sp), placeholder = {
                                    Text(text = "", color = Color.Gray, fontSize = 13.sp)
                                })
                        }
                        Divider(Modifier.fillMaxWidth(), thickness = 2.dp, Color(0XFF0A4635))
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        if(loading){
            Box(
                Modifier
                    .height(50.dp)
                    .width(240.dp)
                    .background(color = Color(0XFF0A4635), shape = RoundedCornerShape(40.dp))
                    .padding(horizontal = 20.dp)
                    .align(Alignment.CenterHorizontally)
                , contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.White, strokeWidth = 5.dp, modifier = Modifier.size(30.dp))
            }
        } else {
            Box(
                Modifier
                    .height(50.dp)
                    .width(240.dp)
                    .background(color = Color(0XFF0A4635), shape = RoundedCornerShape(40.dp))
                    .padding(horizontal = 20.dp)
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        coroutine.launch {
                            if (pickUpViewModel.pickUpState.value.username.isNotEmpty() && optionSelected.isNotEmpty() && pickUpViewModel.pickUpState.value.address.isNotEmpty() && pickUpViewModel.pickUpState.value.activedPhoneNumber.isNotEmpty()) {
                                pickUpViewModel.makeOrder(
                                    email = logInViewModel.fetchData.value!!.email,
                                    userName = pickUpViewModel.pickUpState.value.username,
                                    category = optionSelected,
                                    weight = pickUpViewModel.pickUpState.value.weight,
                                    pickupDate = pickUpViewModel.pickUpState.value.date.toString(),
                                    address = pickUpViewModel.pickUpState.value.address,
                                    phoneNumber = pickUpViewModel.pickUpState.value.activedPhoneNumber,
                                    note = pickUpViewModel.pickUpState.value.additionalNotes
                                )
                            } else {
                                println("tetot")
                            }
                        }
                    }, contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Pick up",
                    fontWeight = FontWeight.Bold,
                    color = Color(0XFFF2E8D8),
                    fontSize = 18.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
    }
    if (showDropdown) {
        DropdownOverlay(
            options = listOf("Limbah Pre-Konsumen", "Limbah Pasca-Konsumen", "Limbah Serat Alamiah", "Limbah Serat Sintesis"),
            onOptionSelected = { option ->
                optionSelected = option
                showDropdown = false
                println("Selected: $option")
            },
            modifier = Modifier
                .offset { IntOffset(dropdownOffset.x.toInt(), dropdownOffset.y.toInt()) }
                .width(with(LocalDensity.current) { dropdownWidth.toDp() })
                .zIndex(1f)
        )
    }
}

@Composable
fun DropdownOverlay(
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Spacer(modifier = Modifier.height(7.dp))
        Box(
            modifier
                .wrapContentSize()
                .background(Color.White, RoundedCornerShape(10.dp))
                .border(2.dp, Color(0xFF0A4635), RoundedCornerShape(10.dp))
        ) {
            Column(Modifier.padding(10.dp)) {
                options.forEach { option ->

                    Text(
                        text = option,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                            .clickable { onOptionSelected(option) },
                        color = Color(0xFF0A4635),
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}