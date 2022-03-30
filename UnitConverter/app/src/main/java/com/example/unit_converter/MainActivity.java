package com.example.unit_converter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // INITIALIZING VARIABLES
    Spinner measurementSpinner;
    ImageButton measurementButton, temperatureButton, weightButton;
    TextView result1, result2, result3, unit1, unit2, unit3;
    EditText inputUnit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // LINKING VARIABLES TO LAYOUT
        inputUnit = (EditText) findViewById(R.id.inputUnit);
        measurementSpinner = (Spinner) findViewById(R.id.selection);
        measurementButton = (ImageButton) findViewById(R.id.measurementButton);
        weightButton = (ImageButton) findViewById(R.id.weightButton);
        temperatureButton = (ImageButton) findViewById(R.id.temperatureButton);
        result1 = (TextView) findViewById(R.id.result1);
        result2 = (TextView) findViewById(R.id.result2);
        result3 = (TextView) findViewById(R.id.result3);
        unit1 = (TextView) findViewById(R.id.unit1);
        unit2 = (TextView) findViewById(R.id.unit2);
        unit3 = (TextView) findViewById(R.id.unit3);

        //SETTING SELECTIONS
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.listMeasurements));
        measurementSpinner.setAdapter(adapter);

        //BUTTON CLICK FUNCTIONS
        measurementButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (measurementSpinner.getSelectedItemPosition() != 0) {
                    Toast.makeText(getApplicationContext(), "Please select the correct conversion icon", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    measurementHandler(Float.parseFloat(inputUnit.getText().toString()));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Please enter a number! Error: "+ e.getClass(), Toast.LENGTH_SHORT).show();
                }

                }

            });

        temperatureButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (measurementSpinner.getSelectedItemPosition() != 1) {
                    Toast.makeText(getApplicationContext(), "Please select the correct conversion icon", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    temperatureHandler(Float.parseFloat(inputUnit.getText().toString()));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Please enter a number! Error: "+ e.getClass(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        weightButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (measurementSpinner.getSelectedItemPosition() != 2) {
                    Toast.makeText(getApplicationContext(), "Please select the correct conversion icon", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    weightHandler(Float.parseFloat(inputUnit.getText().toString()));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Please enter a number! Error Code: "+ e.getClass(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        };

    // CALCULATION FUNCTIONS
    public void measurementHandler (Float input) {
        String centimetre = String.format("%.2f", input*100);
        String foot = String.format("%.2f", input*3.28);
        String inches = String.format("%.2f", input*39.37);
        setResult(centimetre,foot,inches);
        setUnit("Centemetres", "Foot", "Inches");
    }
    public void temperatureHandler (float input) { //do temps
        String fahrenheit = String.format("%.2f", (input *1.8) + 32); //find temps needed
        String kelvin = String.format("%.2f", input+273.15);
        setResult(fahrenheit, kelvin, "");
        setUnit("Fahrenheit", "Kelvin", "");
    }

    public void weightHandler (float input) { //do weights
        String gram = String.format("%.2f", input*1000); //find weight needed
        String ounce = String.format("%.2f", input*35.27);
        String pound = String.format("%.2f", input*2.2);
        setResult(gram,ounce,pound);
        setUnit("Gram", "Ounce(Oz)", "Pound(lb)");


    }

    //HELPER FUNCTIONS
    public void setResult (String val1, String val2, String val3){
            result1.setText(val1);
            result2.setText(val2);
            result3.setText(val3);
    }
    public void setUnit (String val1, String val2, String val3){
            unit1.setText(val1);
            unit2.setText(val2);
            unit3.setText(val3);
    }
}

