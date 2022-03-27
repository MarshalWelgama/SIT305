package com.example.unit_converter;

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
    // initializing variable types
    Spinner measurementSpinner;
    ImageButton measurementButton, temperatureButton, weightButton;
    TextView result1, result2, result3, unit1, unit2, unit3;
    EditText inputUnit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // linking variable to layout
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

        //setting selections for dropdown menu
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.listMeasurements));
        measurementSpinner.setAdapter(adapter);

        measurementButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (measurementSpinner.getSelectedItemPosition() != 0) {
                    Toast.makeText(getApplicationContext(), "Please select the correct conversion icon", Toast.LENGTH_SHORT).show();

                }
                try {
                    measurementHandler(Float.parseFloat(inputUnit.getText().toString()));
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Please enter a number! Error: "+ e.getClass(), Toast.LENGTH_SHORT).show();
                }

                }

            });
        //do onClick for the other buttons
        };


    public void measurementHandler (Float input) {
        String centimetre = String.format("%.2f", input*100);
        String foot = String.format("%.2f", input*3.28);
        String inches = String.format("%.2f", input*39.37);
        result1.setText(centimetre);
        result2.setText(foot);
        result3.setText(inches);
        unit1.setText("Centemetres");
        unit2.setText("Foot");
        unit3.setText("Inches");

    }
    public void temperatureHandler (float input) { //do temps

    }

    public void weightHandler (float input) { //do weights

    }
}

