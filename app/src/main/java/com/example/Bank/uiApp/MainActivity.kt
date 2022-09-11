package com.example.Bank.uiApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.Bank.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView =
            findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, it, _ ->


            when (it.id) {
                R.id.categoriesFragment -> {
                    bottomNavigationView.visibility = View.VISIBLE
                }

                R.id.productDetailsFragment -> {
                    bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.cartsFragment -> {
                    bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.productFragment -> {
                    bottomNavigationView.visibility = View.VISIBLE
                }
                R.id.profileFragment -> {
                    bottomNavigationView.visibility = View.VISIBLE
                }
                else -> bottomNavigationView.visibility = View.GONE
            }
        }


    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainer)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}