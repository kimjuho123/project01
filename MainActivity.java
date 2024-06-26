package com.example.kimjuho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.view.View;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView resultTextView;
    private String currentNumber = "";
    private double result = 0;
    private String operator = "";
    private boolean isOperatorClicked = false;
    Button button;
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);
    }
    public void onNumberButtonClick(View view) {
        if (isOperatorClicked) {
            resultTextView.setText("");
            isOperatorClicked = false;
        }
        String number = ((Button) view).getText().toString();
        currentNumber += number;
        resultTextView.append(number);
    }

    public void onOperatorButtonClick(View view) {
        String newOperator = ((Button) view).getText().toString();
        if (!currentNumber.isEmpty()) {
            calculate();
            operator = newOperator;
            resultTextView.append(newOperator);
            isOperatorClicked = true;
        }
    }

    public void onEqualButtonClick(View view) {
        calculate();
        operator = "";
    }

    private void calculate() {
        if (!currentNumber.isEmpty()) {
            double number = Double.parseDouble(currentNumber);
            if (operator.equals("+")) {
                result += number;
            } else if (operator.equals("-")) {
                result -= number;
            } else if (operator.equals("*")) {
                result *= number;
            } else if (operator.equals("/")) {
                if (number != 0) {
                    result /= number;
                } else {
                    Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                }
            } else {
                result = number;
            }
            currentNumber = "";
            resultTextView.setText(String.valueOf(result));
        }
    }
    public void onBackspaceButtonClick(View view) {
        if(!currentNumber.isEmpty()) {
            currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
            resultTextView.setText(resultTextView.getText().toString().substring(0, resultTextView.getText().length() - 1));
        }
    }
    public void onClearButtonClick(View view) {
        result = 0;
        operator = "";
        currentNumber = "";
        resultTextView.setText("");
    }
}
