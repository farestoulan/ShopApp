package com.example.Bank.models.carts

import com.google.gson.annotations.SerializedName

data class Carts(
    @SerializedName("status")
    var status: Boolean,

    @SerializedName("message")
    var message: String?,

    @SerializedName("data")
    var data:DataCarts


)
