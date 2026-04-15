package com.example.materialcalculator

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment

class ConverterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_converter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val input = view.findViewById<EditText>(R.id.inputValue)
        val convertBtn = view.findViewById<Button>(R.id.convertBtn)
        val result = view.findViewById<TextView>(R.id.resultText)
        val spinner = view.findViewById<Spinner>(R.id.spinner)

        val options = arrayOf(
            "m³ → kg (Cement)",
            "kg → m³ (Cement)",
            "cm → m",
            "m → cm"
        )

        spinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            options
        )

        convertBtn.setOnClickListener {

            val value = input.text.toString().toDoubleOrNull()

            if (value == null) {
                result.text = "Enter valid value"
                return@setOnClickListener
            }

            val output = when (spinner.selectedItem.toString()) {

                "m³ → kg (Cement)" -> value * 1440
                "kg → m³ (Cement)" -> value / 1440
                "cm → m" -> value / 100
                "m → cm" -> value * 100

                else -> 0.0
            }

            result.text = "Result: %.2f".format(output)
        }
    }
}