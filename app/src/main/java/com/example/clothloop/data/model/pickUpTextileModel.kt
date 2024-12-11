package com.example.clothloop.data.model

import java.time.LocalDate

data class pickUpTextileModel(
    val username: String = "",
    val weight: Int = 1,
    val date: LocalDate = LocalDate.now(),
    val address: String = "",
    val activedPhoneNumber: String = "",
    val additionalNotes: String = ""
)