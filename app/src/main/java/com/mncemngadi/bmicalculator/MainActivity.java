package com.mncemngadi.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //Class variables
    private TextView resultViewText;
    private RadioButton maleRadioButton;

    private RadioButton femaleRadioButton;
    private Button calculateButton;
    private RadioButton radioButton;
    private EditText ageEditText;
    private EditText feetEditText;
    private EditText inchesEditText;
    private EditText weightEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        setButtonListener();


    }


    private void findViews(){

        resultViewText = findViewById(R.id.edit_text_result);

        maleRadioButton = findViewById(R.id.radio_button_male);
        femaleRadioButton = findViewById(R.id.radio_button_female);

        ageEditText = findViewById(R.id.edit_text_age);
        feetEditText = findViewById(R.id.edit_text_feet);
        inchesEditText = findViewById(R.id.edit_text_inches);
        weightEditText = findViewById(R.id.edit_text_weight);

        calculateButton = findViewById(R.id.button_calculate);

    }

    private void setButtonListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmiResult = calculateBmi();

                String ageText = ageEditText.getText().toString();
                int age = Integer.parseInt(ageText);

                if (age>=18){

                    displayResults(bmiResult);
                }else {

                    displayGuidance(bmiResult);

                }

            }
        });
    }


    private double calculateBmi() {


        String feetText = feetEditText.getText().toString();
        String inchesText = inchesEditText.getText().toString();
        String weightText = weightEditText.getText().toString();

        //Concerting Text into numbers
        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        int totalInches = (feet * 12) + inches;
        double heightInMeters = totalInches * 0.0254;

        return weight / (heightInMeters * heightInMeters);
    }

    private void displayResults(double bmi){

        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResults = myDecimalFormatter.format(bmi);

        String fullResultString;
        if(bmi < 18.5){
            //Display Underweight
            fullResultString = bmiTextResults + " - You are underweight";

        }else if(bmi > 25){
            //Display Overweight
            fullResultString = bmiTextResults + " - You are overweight";

        }else{
            //Display Healthy
            fullResultString = bmiTextResults + " - You are a healthy weight";

        }

        resultViewText.setText(fullResultString);

    }

    private void displayGuidance(double bmi) {

        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResults = myDecimalFormatter.format(bmi);

        String fullResultString;
        if(maleRadioButton.isChecked()){
            //Display boy guidance
            fullResultString = bmiTextResults + " - As you are under 18 please consult you doctor for the healthy range for boys";

        } else if (femaleRadioButton.isChecked()) {
            //Display girl guidance
            fullResultString = bmiTextResults + " - As you are under 18 please consult you doctor for the healthy range for girls";

        }else{
            //General guidance
            fullResultString = bmiTextResults + " - As soon as you are 18 please consult you doctor for the healthy range";

        }
        resultViewText.setText(fullResultString);

    }

}