package com.example.Bank.DAOClass

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.Bank.Database.User

@Dao
interface UserDAO {
    @Insert
    fun insartAllData(model: User?)

    @Query("select * from register")
    fun getAllData(): List<User?>?


    @Query("select email from register")
    fun getEmail (email: String): User?

    @Query("select password from register")
    fun getPassword (password: String): User?
}