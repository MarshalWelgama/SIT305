package com.example.lostandfound;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lostandfound.data.DatabaseHelper;

public class NewAdvert extends AppCompatActivity {
    EditText NameField;
    EditText PhoneField;
    EditText DescriptionField;
    EditText DateField;
    EditText LocationField;
    Button SaveBtn;
    DatabaseHelper db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newadvert);
        db = new DatabaseHelper(this);
        NameField = (EditText) findViewById(R.id.NameField);
        PhoneField = (EditText) findViewById(R.id.PhoneField);
        DescriptionField = (EditText) findViewById(R.id.DescriptionField);
        DateField = (EditText) findViewById(R.id.DateField);
        LocationField = (EditText) findViewById(R.id.LocationField);
        SaveBtn = (Button) findViewById(R.id.SaveBtn);

        SaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = NameField.getText().toString();
                String phone = PhoneField.getText().toString();
                String Description = DescriptionField.getText().toString();
                String Date = DateField.getText().toString();
                String Location = LocationField.getText().toString();

            }
        });


    }
}