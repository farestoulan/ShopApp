package com.example.Bank.models.products

import com.google.gson.annotations.SerializedName

class Products(
    @SerializedName("status")
    var status: Boolean,
    @SerializedName("message")
    var message: String,
    @SerializedName("data")
    var data: ProductCategories,
)