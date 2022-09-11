package com.example.Bank.models.products

import com.google.gson.annotations.SerializedName

data class ProductCategories(
    @SerializedName("current_page")
    var current_page: String,
    @JvmField
    @SerializedName("data")
    var result: List<ListOfProduct?>? = null,
)
