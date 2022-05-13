package com.example.lostandfound;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lostandfound.data.DatabaseHelper;

public class Individual extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual);
        DatabaseHelper db = new DatabaseHelper(Individual.this);
    }
}
