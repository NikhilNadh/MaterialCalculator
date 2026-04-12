package com.example.materialcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        // Default screen
        loadFragment(CalculatorFragment())
        bottomNav.selectedItemId = R.id.nav_calc

        bottomNav.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.nav_calc -> {
                    loadFragment(CalculatorFragment())
                    true
                }

                R.id.nav_about -> {
                    loadFragment(AboutFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}