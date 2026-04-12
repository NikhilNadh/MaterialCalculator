package com.example.materialcalculator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_about
        bottomNav.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.nav_calc -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    true
                }

                R.id.nav_about -> {
                    true
                }

                else -> false
            }
        }
    }
}