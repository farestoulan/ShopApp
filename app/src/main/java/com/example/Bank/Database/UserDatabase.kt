package com.example.Bank.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.Bank.DAOClass.UserDAO

@Database(entities = [User::class], version = 1)
  abstract class UserDatabase : RoomDatabase() {

 abstract fun getUserDao(): UserDAO
 private var instance: UserDatabase? = null


 open fun getDatabase(context: Context?): UserDatabase? {
  if (instance == null) {
   synchronized(UserDatabase::class.java) {
    instance = Room.databaseBuilder(
     context!!,
     UserDatabase::class.java, "user_database"
    )
     .allowMainThreadQueries()
     .build()
   }
  }
  return instance
 }

}