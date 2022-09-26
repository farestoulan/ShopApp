package com.example.Bank.repository


import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.Bank.api.RetrofitClient
import com.example.Bank.contextApplication.MyApp
import com.example.Bank.models.addcarts.AddCarts
import com.example.Bank.models.carts.Carts
import com.example.Bank.models.categories.Categories
import com.example.Bank.models.products.Products
import com.example.Bank.models.userdata.LoginRespons
import com.example.Bank.storage.SharedPrefManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository {

    val loginResponse = MutableLiveData<LoginRespons?>()
    val userLogin = MutableLiveData<LoginRespons?>()
    val getListOfCategories = MutableLiveData<Categories?>()
    val getListOfProducts = MutableLiveData<Products?>()
    val addCarts = MutableLiveData<AddCarts?>()
    val getCarts = MutableLiveData<Carts?>()


    fun createUser(
        email: String,
        password: String,
        name: String,
        phone: String,
    ): MutableLiveData<LoginRespons?> {

        val call = RetrofitClient.instance!!.api.createUser(email, password, name, phone)
        call?.enqueue(object : Callback<LoginRespons?> {
            override fun onResponse(
                call: Call<LoginRespons?>,
                response: Response<LoginRespons?>,
            ) {
                val dataResponse = response.body()
                val msg = dataResponse?.message
                val status = dataResponse?.status
                val data = dataResponse?.data
                if (data != null) {
                    val responseUserLogin = response.body()
                    SharedPrefManager.getInstance(MyApp.context!!)
                        ?.saveUser(responseUserLogin!!.data!!)
                    loginResponse.value = LoginRespons(status!!, msg!!, data)
                } else {
                    loginResponse.value = LoginRespons(status!!, msg!!, null)
                }

            }

            override fun onFailure(call: Call<LoginRespons?>, t: Throwable) {
                val msgFailure = t.message
                loginResponse.value = LoginRespons(false, msgFailure!!, null)

            }
        })

        return loginResponse
    }


    fun userLogin(
        email: String,
        password: String,
    ): MutableLiveData<LoginRespons?> {
        val call = RetrofitClient.instance!!.api.userLogin(email, password)
        call?.enqueue(object : Callback<LoginRespons?> {
            override fun onResponse(call: Call<LoginRespons?>, response: Response<LoginRespons?>) {
                val dataResponse = response.body()
                val msg = dataResponse?.message
                val status = dataResponse?.status
                val data = dataResponse?.data
                if (data != null) {
                    val responseUserLogin = response.body()
                    SharedPrefManager.getInstance(MyApp.context!!)
                        ?.saveUser(responseUserLogin!!.data!!)
                    userLogin.value = LoginRespons(status!!, msg!!, data)
                } else {
                    userLogin.value = LoginRespons(status!!, msg!!, null)
                }

            }

            override fun onFailure(call: Call<LoginRespons?>, t: Throwable) {
                val msg = t.message
                userLogin.value = LoginRespons(false, msg!!, null)
            }

        })
        return userLogin
    }

    fun getListOfCategories(): MutableLiveData<Categories?> {
        val call = RetrofitClient.instance!!.api.getListOfCategories()
        call?.enqueue(object : Callback<Categories?> {
            override fun onResponse(call: Call<Categories?>, response: Response<Categories?>) {

                val dataResponse = response.body()
                val status = dataResponse?.status
                val data = dataResponse?.data
                getListOfCategories.value = Categories(status!!, null, data!!)

            }

            override fun onFailure(call: Call<Categories?>, t: Throwable) {

            }
        })
        return getListOfCategories
    }

    fun getListOfProducts(category_id: Int): MutableLiveData<Products?> {
        val call = RetrofitClient.instance!!.api.getListOfProducts(category_id)
        call.enqueue(object : Callback<Products?> {
            override fun onResponse(call: Call<Products?>, response: Response<Products?>) {
                val dataResponse = response.body()
                val status = dataResponse?.status
                val data = dataResponse?.data
                getListOfProducts.value = Products(status!!, null, data!!)


            }

            override fun onFailure(call: Call<Products?>, t: Throwable) {

            }
        })

        return getListOfProducts
    }

    fun addCarts(product_id: Int): MutableLiveData<AddCarts?> {
        val call = RetrofitClient.instance?.api?.addCarts(product_id)
        call?.enqueue(object :Callback<AddCarts?>{
            override fun onResponse(call: Call<AddCarts?>, response: Response<AddCarts?>) {
                val dataResponse = response.body()
                val msg = dataResponse?.message
                val status = dataResponse?.status
                val data = dataResponse?.data
                addCarts.value = AddCarts(status!!,msg!!,data!!)

            }

            override fun onFailure(call: Call<AddCarts?>, t: Throwable) {

            }
        })
        return addCarts
    }

    fun getCarts(): MutableLiveData<Carts?> {

        val call = RetrofitClient.instance?.api?.getCarts()

        call?.enqueue(object :Callback<Carts?>{
            override fun onResponse(call: Call<Carts?>, response: Response<Carts?>) {
                val dataResponse = response.body()
                val msg = dataResponse?.message
                val status = dataResponse?.status
                val data = dataResponse?.data

                getCarts.value = Carts(status!!,null,data!!)
            }

            override fun onFailure(call: Call<Carts?>, t: Throwable) {
                Log.i("0000000",t.message.toString())
            }

        })
        return getCarts
    }
}