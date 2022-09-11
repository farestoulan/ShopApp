package com.example.Bank.models.products

import com.google.gson.annotations.SerializedName

data class ListOfProduct(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,

    @SerializedName("image")
    var image: String,

    @SerializedName("price")
    var price: String,
    @SerializedName("description")
    var description: String,
)
