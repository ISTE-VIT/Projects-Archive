package com.example.gtu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

public class SecondScreen extends AppCompatActivity {

    Button button,button2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second_screen);
        Objects.requireNonNull(getSupportActionBar()).hide();
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);


        //---------Skip Button---------//
        button2.setOnClickListener(arg ->
        {
            Intent i = new Intent(SecondScreen.this,Home.class);
            startActivity(i);
            finish();
        });


        //---------Next Button---------//
        button.setOnClickListener(arg ->
        {
            Intent i = new Intent(SecondScreen.this,ThirdScreen.class);
            startActivity(i);
            finish();

        });


    }
}