package com.example.materialcalculator

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment

class CalculatorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)

        val length = view.findViewById<EditText>(R.id.length)
        val width = view.findViewById<EditText>(R.id.width)
        val height = view.findViewById<EditText>(R.id.height)
        val calcBtn = view.findViewById<Button>(R.id.calcBtn)
        val result = view.findViewById<TextView>(R.id.result)

        calcBtn.setOnClickListener {

            val lText = length.text.toString()
            val wText = width.text.toString()
            val hText = height.text.toString()

            if (lText.isEmpty() || wText.isEmpty() || hText.isEmpty()) {
                Toast.makeText(requireContext(), "Enter all values", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                val l = lText.toDouble()
                val w = wText.toDouble()
                val h = hText.toDouble()

                val volume = l * w * h

                val cement = (1.0 / 5.5) * volume * 1440
                val sand = (1.5 / 5.5) * volume
                val aggregate = (3.0 / 5.5) * volume

                result.text = """
                    Volume: $volume m³
                    Cement: $cement kg
                    Sand: $sand m³
                    Aggregate: $aggregate m³
                """.trimIndent()

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Invalid input", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}