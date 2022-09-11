package com.example.Bank.models.categories

import com.google.gson.annotations.SerializedName

data class ListOfCategories(

    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,

    @SerializedName("image")
    var image: String,



)



