package com.example.Bank.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "register")
 class User() {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L

    @ColumnInfo(name = "name")
    private var name: String? = null

    @ColumnInfo(name = "email")
    private var email: String? = null

    @ColumnInfo(name = "password")
    private var password: String? = null


    @JvmName("getKey1")
    fun getId(): Long {
        return id
    }

    @JvmName("setKey1")
    fun setId(id: Long) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }

}