package com.example.Bank.viewModel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.Bank.models.addcarts.AddCarts
import com.example.Bank.models.carts.Carts
import com.example.Bank.models.categories.Categories
import com.example.Bank.models.products.Products
import com.example.Bank.models.userdata.LoginRespons
import com.example.Bank.repository.Repository


class MainViewModel : ViewModel() {

    var myResponseCreateUser: MutableLiveData<LoginRespons?> = MutableLiveData()
    var myResponseUserLogin: MutableLiveData<LoginRespons?> = MutableLiveData()
    var myResponseGetListOfCategories: MutableLiveData<Categories?> = MutableLiveData()
    var myResponseGetListOfProducts: MutableLiveData<Products?> = MutableLiveData()
    var myResponseAddCarts: MutableLiveData<AddCarts?> = MutableLiveData()
    var myResponseGetCarts: MutableLiveData<Carts?> = MutableLiveData()


    fun createUser(
        email: String,
        password: String,
        name: String,
        phone: String,
    ): MutableLiveData<LoginRespons?> {

        myResponseCreateUser = Repository.createUser(email, password, name, phone)
        return myResponseCreateUser

    }


    fun userLogin(
        email: String,
        password: String,
    ): MutableLiveData<LoginRespons?> {

        myResponseUserLogin = Repository.userLogin(email, password)
        return myResponseUserLogin

    }


    fun getListOfCategories(): MutableLiveData<Categories?> {

        myResponseGetListOfCategories = Repository.getListOfCategories()
        return myResponseGetListOfCategories
    }

    fun getListOfProducts(category_id: Int):MutableLiveData<Products?> {

        myResponseGetListOfProducts = Repository.getListOfProducts(category_id)

        return myResponseGetListOfProducts
    }


    fun addCarts(product_id: Int):MutableLiveData<AddCarts?> {
      myResponseAddCarts = Repository.addCarts(product_id)

        return myResponseAddCarts
    }

    fun getCarts(): MutableLiveData<Carts?> {

        myResponseGetCarts = Repository.getCarts()

        return myResponseGetCarts
    }


}