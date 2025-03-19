package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.Utils
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private var selectedBarWeight = 45 // Default bar weight is 45 lbs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightInput = findViewById<EditText>(R.id.weightInput)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val warmupOutput = findViewById<TextView>(R.id.warmupOutput)
        val plateOutput = findViewById<TextView>(R.id.plateOutput)
        val bar45Button = findViewById<Button>(R.id.bar45Button)
        val bar35Button = findViewById<Button>(R.id.bar35Button)
        val calculateWarmupsButton = findViewById<Button>(R.id.showWarmupButton)
        val percentageInput = findViewById<EditText>(R.id.percentageInput)
        val calculatePercentageButton = findViewById<Button>(R.id.calculatePercentageButton)
        val percentageOutput = findViewById<TextView>(R.id.percentageOutput)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.nav_view)

        var isHighlighted = false
        var calculateWarmups = false

        calculateWarmupsButton.setOnClickListener {
            calculateWarmups = !calculateWarmups
            Toast.makeText(this, "Show Warmup Sets", Toast.LENGTH_SHORT).show()
            isHighlighted = !isHighlighted
            if (isHighlighted) {
                calculateWarmupsButton.setBackgroundColor(Color.parseColor("#FFCC00")) // Highlight color
            } else {
                calculateWarmupsButton.setBackgroundColor(Color.parseColor("#CCCCCC")) // Default color
            }
        }

        bar45Button.setOnClickListener {
            selectedBarWeight = 45
            Toast.makeText(this, "45 lb bar selected", Toast.LENGTH_SHORT).show()
        }

        bar35Button.setOnClickListener {
            selectedBarWeight = 35
            Toast.makeText(this, "35 lb bar selected", Toast.LENGTH_SHORT).show()
        }

        val availablePlates = listOf(45.0, 25.0, 10.0, 5.0, 2.5) // Default plates
        val warmupPercentages = listOf(0.45, 0.6, 0.75, 0.9)

        calculateButton.setOnClickListener {
            plateOutput.text = ""
            warmupOutput.text = "Warm-up sets will appear here"
            val workingWeight = weightInput.text.toString().toDoubleOrNull()
            if (workingWeight != null) {
                val barWeight = selectedBarWeight.toDouble()

                if (calculateWarmups) {
                    val warmupSets = Utils.calculateWarmups(workingWeight, warmupPercentages)
                    val warmupDetails = warmupSets.mapIndexed { index, warmup ->
                        val percentage = warmupPercentages[index] * 100 // Convert to percentage
                        val plates = Utils.calculatePlates(warmup, barWeight, availablePlates)
                        "Warm-up at ${percentage.toInt()}%: Plates: ${plates.joinToString(", ")} lbs"
                    }.joinToString("\n")
                    warmupOutput.text = warmupDetails
                }

                // Calculate plates
                val plates = Utils.calculatePlates(workingWeight, barWeight, availablePlates)
                plateOutput.text = "Working Set: ${plates.joinToString(", ")} lbs"
            } else {
                warmupOutput.text = "Please enter a valid weight!"
                plateOutput.text = ""
            }
        }

        calculatePercentageButton.setOnClickListener {
            val inputText = percentageInput.text.toString()
            if (inputText.isNotEmpty()) {
                val number = inputText.toDoubleOrNull()
                if (number != null) {
                    val result = number * 0.8
                    percentageOutput.text = "$result"
                }
            } else {
                percentageOutput.text = "Please enter a number"
            }
        }
    }
}