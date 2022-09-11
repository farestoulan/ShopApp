package com.example.Bank.api


import com.example.Bank.models.addcarts.AddCarts
import com.example.Bank.models.carts.Carts
import com.example.Bank.models.categories.Categories
import com.example.Bank.models.products.Products
import com.example.Bank.models.userdata.LoginRespons
import retrofit2.Call
import retrofit2.http.*


interface Api {

    @FormUrlEncoded
    @POST("register")
    fun createUser(
        @Field("email") email: String?,
        @Field("password") password: String?,
        @Field("name") name: String?,
        @Field("phone") phone: String?
    ): Call<LoginRespons?>?

    @FormUrlEncoded
    @POST("login")
    fun userLogin(
        @Field("email") email :String,
        @Field("password") password :String,

        ): Call<LoginRespons?>?


        @GET("categories")
    fun getListOfCategories(): Call<Categories?>?


    @GET("products")
    fun getListOfProducts(
        @Query("category_id") category_id: Int
    ): Call<Products?>


    @FormUrlEncoded
    @POST("carts")
    fun addCarts(
        @Field("product_id") product_id :Int,
        ): Call<AddCarts?>?

    @GET("carts")
    fun getCarts():Call<Carts?>?

}