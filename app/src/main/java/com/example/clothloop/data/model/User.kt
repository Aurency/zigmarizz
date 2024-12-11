package com.example.clothloop.data.model

data class User(
    val name: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val birthDate: String = "",
    val balance: Int = 0,
    val address: String = "",
    val totalWeightSelled: Int = 0
)
