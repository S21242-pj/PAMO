package com.example.tipper

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// for EditText event handling
// EditText listener
// for bill amount input
// for displaying text
class MainActivity : AppCompatActivity() {
    private var weight = 0.0
    private var height = 0.15

    //TextViews definitions
    private var heightTextView: TextView? = null
    private var weightTextView: TextView? = null
    private var totalTextView: TextView? = null

    //method onCreate, most important one
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // call superclass onCreate
        setContentView(R.layout.activity_main) // inflate the GUI

//looking up TextViews in layout
        weightTextView = findViewById<View>(R.id.weightTextView) as TextView
        heightTextView = findViewById<View>(R.id.heightTextView) as TextView
        totalTextView = findViewById<View>(R.id.totalTextView) as TextView
        totalTextView!!.text = String.format(0.toString() + "")
        val button_to_bh_calc = findViewById<View>(R.id.button_to_bh_calc) as Button
        val button_to_recommendations = findViewById<View>(R.id.button_to_recommendations) as Button
        val button_to_diagram = findViewById<View>(R.id.button_to_diagram) as Button
        val button_to_quiz = findViewById<View>(R.id.button_to_quiz) as Button
        button_to_quiz.setOnClickListener { openQuiz() }
        button_to_diagram.setOnClickListener { openDiagram() }
        button_to_bh_calc.setOnClickListener { openCaloriesCalc() }
        button_to_recommendations.setOnClickListener { openRecommendation() }


// setting TextWatchers for both weight and height fields
        val weightEditText = findViewById<View>(R.id.weightEditText) as EditText
        weightEditText.addTextChangedListener(weightEditTextWatcher)
        val heightEditText = findViewById<View>(R.id.heightEditText) as EditText
        heightEditText.addTextChangedListener(heightEditTextWatcher)
    }

    //method calculate, calculating BMI, setting totalTextView as result
    private fun calculate() {
        val total = weight / (height * height)
        totalTextView!!.text = String.format(total.toString() + "")
    }

    // listeners object for TextViews weight and height text-changed events
    private val weightEditTextWatcher: TextWatcher = object : TextWatcher {
        // called when the user modifies weight or height field, or both
        override fun onTextChanged(s: CharSequence, start: Int,
                                   before: Int, count: Int) {
            try {
                weight = s.toString().toDouble() / 100.0
                weightTextView!!.text = String.format(weight.toString() + "")
            } catch (e: NumberFormatException) {
                weightTextView!!.text = ""
                weight = 0.0
            }
            //recalculate on change
            calculate()
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int) {
        }
    }
    private val heightEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int,
                                   before: Int, count: Int) {
            try {
                height = s.toString().toDouble() / 100.0
                heightTextView!!.text = String.format(height.toString() + "")
            } catch (e: NumberFormatException) {
                heightTextView!!.text = ""
                height = 0.0
            }
            //recalculate on change
            calculate()
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int) {
        }
    }

    fun openCaloriesCalc() {
        val intent = Intent(this, CaloriesCalc::class.java)
        startActivity(intent)
    }

    fun openQuiz() {
        val intent = Intent(this, Quiz::class.java)
        startActivity(intent)
    }

    fun openRecommendation() {
        val intent = Intent(this, Recommendations::class.java)
        startActivity(intent)
    }

    fun openDiagram() {
        val intent = Intent(this, Diagram::class.java)
        startActivity(intent)
    }
}