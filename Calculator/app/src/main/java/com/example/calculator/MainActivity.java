package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText value1EditText, value2EditText;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        value1EditText = findViewById(R.id.value1EditText);
        value2EditText = findViewById(R.id.value2EditText);
        resultTextView = findViewById(R.id.resultTextView);

        Button addButton = findViewById(R.id.addButton);
        Button subtractButton = findViewById(R.id.subtractButton);

        addButton.setOnClickListener(v -> calculate('+'));
        subtractButton.setOnClickListener(v -> calculate('-'));
    }

    private void calculate(char operation) {
        try {
            double value1 = Double.parseDouble(value1EditText.getText().toString());
            double value2 = Double.parseDouble(value2EditText.getText().toString());
            double result = 0;

            switch (operation) {
                case '+':
                    result = value1 + value2;
                    break;
                case '-':
                    result = value1 - value2;
                    break;
            }

            resultTextView.setText(String.format("Your result is: %.2f", result)); // %.2f is used for the double to get the results
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter valid numbers in the text field", Toast.LENGTH_SHORT).show();
        }
    }
}