package com.example.materialcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val calculatorFragment = CalculatorFragment()
    private val aboutFragment = AboutFragment()
    private val converterFragment = ConverterFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        // Load default fragment only once
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, calculatorFragment, "CALC")
            .commit()

        bottomNav.selectedItemId = R.id.nav_calc

        bottomNav.setOnItemSelectedListener {

            val transaction = supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    android.R.anim.fade_in,
                    android.R.anim.fade_out
                )

            when (it.itemId) {

                R.id.nav_calc -> {
                    hideAllFragments(transaction)
                    transaction.show(calculatorFragment)
                }

                R.id.nav_about -> {
                    if (!aboutFragment.isAdded) {
                        transaction.add(R.id.fragmentContainer, aboutFragment, "ABOUT")
                    }
                    hideAllFragments(transaction)
                    transaction.show(aboutFragment)
                }

                R.id.nav_converter -> {
                    if (!converterFragment.isAdded) {
                        transaction.add(R.id.fragmentContainer, converterFragment, "CONVERTER")
                    }
                    hideAllFragments(transaction)
                    transaction.show(converterFragment)
                }
            }

            transaction.commit()
            true
        }
    }

    private fun hideAllFragments(transaction: FragmentTransaction) {
        supportFragmentManager.fragments.forEach {
            transaction.hide(it)
        }
    }
}