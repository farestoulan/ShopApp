package com.example.Bank.models.carts

import com.google.gson.annotations.SerializedName

data class Products(

    @SerializedName("id")
    var id :Int,

    @SerializedName("price")
    var price :Int,

    @SerializedName("old_price")
    var old_price :Int,

    @SerializedName("discount")
    var discount :Int,

    @SerializedName("image")
    var image :String,

    @SerializedName("name")
    var name :String,

    @SerializedName("description")
    var description :String,

    @SerializedName("images")
    var images :List<String>,

    @SerializedName("in_favorites")
    var in_favorites :Boolean,

    @SerializedName("in_cart")
    var in_cart :Boolean,


)
