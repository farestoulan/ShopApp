package com.example.Bank.models.categories

import com.google.gson.annotations.SerializedName

class Categories(
    @SerializedName("status")
    var status: Boolean,
    @SerializedName("message")
    var message: String,
    @SerializedName("data")
    var data: CategoriesData,
)