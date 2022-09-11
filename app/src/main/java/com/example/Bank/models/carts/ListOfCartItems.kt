package com.example.Bank.models.carts

import com.google.gson.annotations.SerializedName

data class ListOfCartItems(

    @SerializedName("id")
    var id: Int,
    @SerializedName("quantity")
    var quantity: Int,

    @SerializedName("product")
    var product: Products,
)
