package com.example.Bank.models.userdata


data class LoginRespons(
    val status: Boolean,
    val message: String,
    val data: UserData,
)
