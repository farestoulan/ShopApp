package com.example.Bank.models.carts

import com.google.gson.annotations.SerializedName

data class DataCarts(
    @SerializedName("cart_items")
    var cart_items: List<ListOfCartItems>,

    @SerializedName("sub_total")
    var sub_total: Int,

    @SerializedName("total")
    var total: Int,

)
