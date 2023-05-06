package com.example.tipper

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CaloriesCalc : AppCompatActivity() {
    private var weight = 0.0
    private var height = 0.15
    private var age = 0
    private var heightTextView: TextView? = null
    private var weightTextView: TextView? = null
    private var ageTextView: TextView? = null
    private var totalTextView: TextView? = null
    var radioGroup: RadioGroup? = null
    var radioButton: RadioButton? = null
    var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calories_calc)
        radioGroup = findViewById(R.id.radioGroup)
        weightTextView = findViewById<View>(R.id.weightTextView) as TextView
        heightTextView = findViewById<View>(R.id.heightTextView) as TextView
        ageTextView = findViewById<View>(R.id.ageTextView) as TextView
        totalTextView = findViewById<View>(R.id.totalTextView) as TextView
        totalTextView!!.text = String.format(0.toString() + "")
        val weightEditText = findViewById<View>(R.id.weightEditText) as EditText
        weightEditText.addTextChangedListener(weightEditTextWatcher)
        val heightEditText = findViewById<View>(R.id.heightEditText) as EditText
        heightEditText.addTextChangedListener(heightEditTextWatcher)
        val ageEditText = findViewById<View>(R.id.ageEditText) as EditText
        ageEditText.addTextChangedListener(ageEditTextWatcher)
        radioGroup = findViewById(R.id.radioGroup)
        textView = findViewById(R.id.text_view_selected)
        val buttonApply = findViewById<Button>(R.id.button_apply)
        buttonApply.setOnClickListener {
            val radioId = radioGroup.getCheckedRadioButtonId()
            radioButton = findViewById(radioId)
            textView.setText("Your choice: " + radioButton.getText())
        }
    }

    private fun calculate() {
        val total = weight / (height * height)
        totalTextView!!.text = String.format(total.toString() + "")
    }

    private val weightEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int,
                                   before: Int, count: Int) {
            try {
                weight = s.toString().toDouble()
                weightTextView!!.text = String.format(weight.toString() + "")
            } catch (e: NumberFormatException) {
                weightTextView!!.text = ""
                weight = 0.0
            }
            onRadioButtonClickedCalculate()
        }

        override fun afterTextChanged(s: Editable) {}
        override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int) {
        }
    }
    private val heightEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int,
                                   before: Int, count: Int) {
            try {
                height = s.toString().toDouble()
                heightTextView!!.text = String.format(height.toString() + "")
            } catch (e: NumberFormatException) {
                heightTextView!!.text = ""
                height = 0.0
            }
            onRadioButtonClickedCalculate()
        }

        override fun afterTextChanged(s: Editable) {}
    }
    private val ageEditTextWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int,
                                   before: Int, count: Int) {
            try {
                age = s.toString().toInt()
                ageTextView!!.text = String.format(age.toString() + "")
            } catch (e: NumberFormatException) {
                age = 0
            }
            onRadioButtonClickedCalculate()
        }

        override fun afterTextChanged(s: Editable) {}
    }

    fun onRadioButtonClickedCalculate() {
        val radioId = radioGroup!!.checkedRadioButtonId
        radioButton = findViewById(R.id.rid1)
        val checked = radioButton.isChecked()
        var total = 0.00
        total = if (checked) {
            88.362 + 13.397 * weight + 4.799 * height - 5.677 * age
        } else {
            447.593 + 9.247 * weight + 3.098 * height - 4.330 * age
        }
        totalTextView!!.text = String.format(total.toString() + "")
    }

    fun checkButton(v: View?) {
        val radioId = radioGroup!!.checkedRadioButtonId
        radioButton = findViewById(radioId)
        Toast.makeText(this, "Selected Radio Button: " + radioButton.getText(),
                Toast.LENGTH_SHORT).show()
    }
}