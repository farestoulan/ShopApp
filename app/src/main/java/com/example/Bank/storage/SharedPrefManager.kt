package com.example.Bank.storage

import android.annotation.SuppressLint
import android.content.Context
import com.example.Bank.models.userdata.UserData

class SharedPrefManager private constructor(private val mCtx: Context) {
    fun saveUser(userData: UserData) {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("id", userData.id)
        editor.putString("email", userData.email)
        editor.putString("name", userData.name)
        editor.putString("image", userData.image)
        editor.putString("token", userData.token)
        editor.putString("credit", userData.credit)

        editor.apply()
    }

    val isLoggedIn: Boolean
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getInt("id", -1) != -1
        }
    val userData: UserData
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return UserData(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("name","null")!!,
                sharedPreferences.getString("email", "null")!!,
                sharedPreferences.getString("phone", "null")!!,
                sharedPreferences.getString("image", "null")!!,
                sharedPreferences.getString("points", "null")!!,
                sharedPreferences.getString("credit", "null")!!,
                sharedPreferences.getString("token","null")!!


                )
        }

    fun clear() {
        val sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    companion object {
        private const val SHARED_PREF_NAME = "my_shared_preff"
        @SuppressLint("StaticFieldLeak")
        private var mInstance: SharedPrefManager? = null
        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager? {
            if (mInstance == null) {
                mInstance = SharedPrefManager(mCtx)
            }
            return mInstance
        }
    }
}