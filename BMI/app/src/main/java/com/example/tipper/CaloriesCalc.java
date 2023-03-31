package com.example.tipper;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CaloriesCalc extends AppCompatActivity {

    private double weight = 0.0;
    private double height = 0.15;
    private int age = 0;

    private TextView heightTextView;
    private TextView weightTextView;
    private TextView ageTextView;
    private TextView totalTextView;
    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calories_calc);

        radioGroup = findViewById(R.id.radioGroup);
        weightTextView = (TextView) findViewById(R.id.weightTextView);
        heightTextView = (TextView) findViewById(R.id.heightTextView);
        ageTextView = (TextView) findViewById(R.id.ageTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);
        totalTextView.setText(String.format(0 + ""));

        EditText weightEditText =
                (EditText) findViewById(R.id.weightEditText);
        weightEditText.addTextChangedListener(weightEditTextWatcher);
        EditText heightEditText =
                (EditText) findViewById(R.id.heightEditText);
        heightEditText.addTextChangedListener(heightEditTextWatcher);

        EditText ageEditText =
                (EditText) findViewById(R.id.ageEditText);
        ageEditText.addTextChangedListener(ageEditTextWatcher);


        radioGroup = findViewById(R.id.radioGroup);
        textView = findViewById(R.id.text_view_selected);
        Button buttonApply = findViewById(R.id.button_apply);
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioId = radioGroup.getCheckedRadioButtonId();

                radioButton = findViewById(radioId);

                textView.setText("Your choice: " + radioButton.getText());
            }
        });

    }

    private void calculate() {
        double total = weight / (height * height);
        totalTextView.setText(String.format(total + ""));
    }

    private final TextWatcher weightEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try {
                weight = Double.parseDouble(s.toString());
                weightTextView.setText(String.format(weight + ""));

            } catch (NumberFormatException e) {
                weightTextView.setText("");
                weight = 0.0;

            }

            onRadioButtonClickedCalculate();
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
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try {
                height = Double.parseDouble(s.toString());
                heightTextView.setText(String.format(height + ""));
            } catch (NumberFormatException e) {
                heightTextView.setText("");
                height = 0.0;
            }

            onRadioButtonClickedCalculate();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private final TextWatcher ageEditTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {

            try {
                age = (int) (Integer.parseInt(s.toString()));
                ageTextView.setText(String.format(age + ""));
            } catch (NumberFormatException e) {
                age = 0;
            }

            onRadioButtonClickedCalculate();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public void onRadioButtonClickedCalculate() {
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(R.id.rid1);

        boolean checked = radioButton.isChecked();

        double total = 0.00;

        if (checked) {
            total = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age);
        } else {
            total = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age);
        }

        totalTextView.setText(String.format(total + ""));
    }


    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        Toast.makeText(this, "Selected Radio Button: " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();
    }

}
