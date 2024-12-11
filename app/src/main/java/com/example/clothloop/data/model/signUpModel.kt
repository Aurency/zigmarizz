package com.example.clothloop.data.model

import java.time.LocalDate

data class SignUp(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val phoneNumber: String = "",
    val newAddress: String = "",
    val birthDate: LocalDate = LocalDate.now(),
    val emailError: String = "",
    val passwordError: String = "",
    val confirmPasswordError: String = "",
    val nameError: String = "",
    val phoneNumberError: String = "",
    val isEmailExists: Boolean = false
)