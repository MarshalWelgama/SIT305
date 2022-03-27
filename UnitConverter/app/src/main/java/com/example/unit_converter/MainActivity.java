package com.example.unit_converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    Spinner measurementSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        measurementSpinner = (Spinner) findViewById(R.id.selection);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.listMeasurements));
        measurementSpinner.setAdapter(adapter);
    }
}