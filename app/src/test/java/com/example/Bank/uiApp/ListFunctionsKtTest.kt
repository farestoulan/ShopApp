package com.example.Bank.uiApp

import org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.random.Random

@RunWith(JUnit4::class)
class ListFunctionsKtTest {

    @Test
    fun `filter() if data are empty or null then return empty list`() {
        val users = emptyList<User>()

        val unit = { user: User -> user.name == "mohamed" }
        val returnedData = filter(users, unit)
        val expectedData = emptyList<User>()

        assertEquals(expectedData, returnedData)
    }

    @Test
    fun `filter() if data are not null then return filtered list`() {
        val users = mutableListOf(User("mohamed", 1), User("ahmed", 2))

        val unit = { user: User -> user.name == "mohamed" }
        val returnedData = filter(users, unit)
        val expectedData = listOf(User("mohamed", 1))

        assertEquals(expectedData, returnedData)
    }
}

data class User(
    val name: String,
    val id: Int
)