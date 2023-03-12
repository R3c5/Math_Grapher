package com.example1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.nio.channels.InterruptedByTimeoutException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button FctElemButton = (Button) findViewById(R.id.FctElemButton);
        FctElemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Start_Fct_Elem();
            }
        });
        Button GraficeButton = (Button) findViewById(R.id.GraficeButton);
        GraficeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Start_Graph();
            }
        });
    }

    private void Start_Fct_Elem() {
        Intent intent = new Intent(this, AlegeGraficFunctieElem.class);
        startActivity(intent);
    }

    private void Start_Graph() {
        Intent intent = new Intent(this, GraphCalculator.class);
        startActivity(intent);
    }
}