package com.example.tipper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable; // for EditText event handling
import android.text.TextWatcher; // EditText listener
import android.view.View;
import android.widget.Button;
import android.widget.EditText; // for bill amount input
import android.widget.TextView; // for displaying text


public class MainActivity extends AppCompatActivity {

    private double weight = 0.0;
    private double height = 0.15;
    //TextViews definitions
    private TextView heightTextView;
    private TextView weightTextView;


    private TextView totalTextView;

    //method onCreate, most important one
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // call superclass onCreate
        setContentView(R.layout.activity_main); // inflate the GUI

//looking up TextViews in layout
        weightTextView = (TextView) findViewById(R.id.weightTextView);
        heightTextView = (TextView) findViewById(R.id.heightTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);
        totalTextView.setText(String.format(0 + ""));

        Button button_to_bh_calc = (Button) findViewById(R.id.button_to_bh_calc);
        Button button_to_recommendations = (Button) findViewById(R.id.button_to_recommendations);
        Button button_to_diagram = (Button) findViewById(R.id.button_to_diagram);
        Button button_to_quiz = (Button) findViewById(R.id.button_to_quiz);



        button_to_quiz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openQuiz();
            }
        });
        button_to_diagram.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openDiagram();
            }
        });

        button_to_bh_calc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openCaloriesCalc();
            }
        });

        button_to_recommendations.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openRecommendation();
            }
        });




// setting TextWatchers for both weight and height fields
        EditText weightEditText =
                (EditText) findViewById(R.id.weightEditText);
        weightEditText.addTextChangedListener(weightEditTextWatcher);
        EditText heightEditText =
                (EditText) findViewById(R.id.heightEditText);
        heightEditText.addTextChangedListener(heightEditTextWatcher);

    }

    //method calculate, calculating BMI, setting totalTextView as result
    private void calculate() {


        double total = weight / (height * height);


        totalTextView.setText(String.format(total + ""));
    }


    // listeners object for TextViews weight and height text-changed events
    private final TextWatcher weightEditTextWatcher = new TextWatcher() {
        // called when the user modifies weight or height field, or both
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try {
                weight = Double.parseDouble(s.toString()) / 100.0;
                weightTextView.setText(String.format(weight + ""));
            } catch (NumberFormatException e) {
                weightTextView.setText("");
                weight = 0.0;
            }
//recalculate on change
            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) {
        }
    };
    private final TextWatcher heightEditTextWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try {
                height = Double.parseDouble(s.toString()) / 100.0;
                heightTextView.setText(String.format(height + ""));
            } catch (NumberFormatException e) {
                heightTextView.setText("");
                height = 0.0;
            }
//recalculate on change
            calculate();
        }

        @Override
        public void afterTextChanged(Editable s) {
        }

        @Override
        public void beforeTextChanged(
                CharSequence s, int start, int count, int after) {
        }
    };


    public void openCaloriesCalc(){
        Intent intent = new Intent(this, CaloriesCalc.class);
        startActivity(intent);
    }

    public void openQuiz(){
        Intent intent = new Intent(this, Quiz.class);
        startActivity(intent);
    }

    public void openRecommendation(){
        Intent intent = new Intent(this, Recommendations.class);
        startActivity(intent);
    }

    public void openDiagram(){
        Intent intent = new Intent(this, Diagram.class);
        startActivity(intent);
    }
}
