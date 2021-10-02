package com.example.gtu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

public class email extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second_screen);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_email);

        Button button7 = findViewById(R.id.button7);
        Button button2 = findViewById(R.id.button2);

        button7.setOnClickListener(arg ->
        {
            Toast.makeText(this, "Coming Soon ", Toast.LENGTH_LONG).show();
        });

        button2.setOnClickListener(arg ->
        {
           Intent i = new Intent(getApplicationContext(),Home.class);
           startActivity(i);
           finish();
        });
    }
}