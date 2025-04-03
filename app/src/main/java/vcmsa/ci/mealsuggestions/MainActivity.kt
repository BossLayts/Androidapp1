package com.example.mealsuggestionapp

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import vcmsa.ci.mealsuggestions.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Reference UI elements
        val editTextTimeOfDay = findViewById<TextInputEditText>(R.id.editTextTimeOfDay)
        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)
        val textViewMealSuggestion = findViewById<TextView>(R.id.textViewMealSuggestion)
        val buttonReset = findViewById<Button>(R.id.buttonReset)

        // Meal suggestions based on the time of day
        val mealSuggestions = mapOf(
            "morning" to "🍳 Pancakes with honey & a glass of orange juice",
            "mid-morning" to "🥑 Avocado toast with black coffee",
            "afternoon" to "🍔 Grilled chicken sandwich with fresh salad",
            "dinner" to "🍣 Sushi with miso soup & green tea"
        )

        // Submit button logic
        buttonSubmit.setOnClickListener {
            val userInput = editTextTimeOfDay.text.toString().trim().lowercase()
            val mealSuggestion = mealSuggestions[userInput]

            textViewMealSuggestion.text = mealSuggestion?.let {
                "Suggested Meal: $it"
            } ?: "⚠ Invalid time of day! Try: Morning, Mid-morning, Afternoon, or Dinner."

            // Hide keyboard after input
            hideKeyboard(it)
        }

        // Reset button logic
        buttonReset.setOnClickListener {
            editTextTimeOfDay.text?.clear()
            textViewMealSuggestion.text = "Meal suggestion will appear here"
        }
    }

    // Function to hide the keyboard after user presses the button
    private fun hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
