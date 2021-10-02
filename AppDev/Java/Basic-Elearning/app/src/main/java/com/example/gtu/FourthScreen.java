package com.example.gtu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Objects;

public class FourthScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second_screen);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_fourth_screen);

        Button button6 = findViewById(R.id.button6);

        button6.setOnClickListener(arg ->
        {
            Intent i = new Intent(FourthScreen.this,email.class);
            startActivity(i);
            finish();
        });


    }
}