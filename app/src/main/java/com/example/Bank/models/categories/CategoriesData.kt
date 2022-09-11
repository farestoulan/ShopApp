package com.example.Bank.models.categories

import com.google.gson.annotations.SerializedName


data class CategoriesData(
    @SerializedName("current_page")
    var current_page: String,
    @JvmField
    @SerializedName("data")
    var results: List<ListOfCategories>,


    )
