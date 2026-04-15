package com.example.materialcalculator

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment

class CalculatorFragment : Fragment() {

    var cementRatio = 1.0
    var sandRatio = 1.5
    var aggRatio = 3.0
    var currentGrade = "M20"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val length = view.findViewById<EditText>(R.id.length)
        val width = view.findViewById<EditText>(R.id.width)
        val height = view.findViewById<EditText>(R.id.height)
        val calcBtn = view.findViewById<Button>(R.id.calcBtn)
        val gradeBtn = view.findViewById<Button>(R.id.gradeBtn)
        val result = view.findViewById<TextView>(R.id.result)

        // 🔵 Grade Selection
        gradeBtn.setOnClickListener {

            val grades = arrayOf(
                "M5 (1:5:10)",
                "M7.5 (1:4:8)",
                "M10 (1:3:6)",
                "M15 (1:2:4)",
                "M20 (1:1.5:3)",
                "M25 (1:1:2)"
            )

            AlertDialog.Builder(requireContext())
                .setTitle("Select Concrete Grade")
                .setItems(grades) { _, which ->

                    when (which) {
                        0 -> { cementRatio = 1.0; sandRatio = 5.0; aggRatio = 10.0; currentGrade = "M5" }
                        1 -> { cementRatio = 1.0; sandRatio = 4.0; aggRatio = 8.0; currentGrade = "M7.5" }
                        2 -> { cementRatio = 1.0; sandRatio = 3.0; aggRatio = 6.0; currentGrade = "M10" }
                        3 -> { cementRatio = 1.0; sandRatio = 2.0; aggRatio = 4.0; currentGrade = "M15" }
                        4 -> { cementRatio = 1.0; sandRatio = 1.5; aggRatio = 3.0; currentGrade = "M20" }
                        5 -> { cementRatio = 1.0; sandRatio = 1.0; aggRatio = 2.0; currentGrade = "M25" }
                    }

                    gradeBtn.text = "Select Grade ($currentGrade)"
                }
                .show()
        }

        // 🔥 REAL-WORLD CALCULATION
        calcBtn.setOnClickListener {

            val l = length.text.toString().toDoubleOrNull()
            val w = width.text.toString().toDoubleOrNull()
            val h = height.text.toString().toDoubleOrNull()

            if (l == null || w == null || h == null) {
                result.text = "Please enter all values"
                return@setOnClickListener
            }

            val wetVolume = l * w * h
            val dryVolume = wetVolume * 1.54

            val totalRatio = cementRatio + sandRatio + aggRatio

            val cementVolume = (cementRatio / totalRatio) * dryVolume
            val sand = (sandRatio / totalRatio) * dryVolume
            val aggregate = (aggRatio / totalRatio) * dryVolume

            val cementWeight = cementVolume * 1440
            val cementBags = cementVolume / 0.035

            result.text = """
            Grade : $currentGrade

            Wet Volume : %.2f m³
            Dry Volume : %.2f m³
            Total Ratio : %.1f

            Cement : %.2f m³
            Sand   : %.2f m³
            Aggregate : %.2f m³

            Cement Bags : %.0f bags
            """.trimIndent().format(
                wetVolume,
                dryVolume,
                totalRatio,
                cementVolume,
                sand,
                aggregate,
                cementBags
            )
        }
    }
}