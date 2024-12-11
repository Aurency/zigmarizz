package com.example.clothloop.data.model

data class Order(
    val orderId: String = "",
    val address: String = "",
    val email: String = "",
    val userName: String = "",
    val pickupdate: String = "",
    val category: String = "",
    val phoneNumber: String = "",
    val note: String = "",
    val status: String = "",
    val weight: Int = 0
)
