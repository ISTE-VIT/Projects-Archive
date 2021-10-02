package com.example.gtu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Objects;

public class ThirdScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_third_screen);

        Button button4 = findViewById(R.id.button4);

        button4.setOnClickListener(arg ->
        {
            Intent i = new Intent(ThirdScreen.this,FourthScreen.class);
            startActivity(i);
            finish();
        });

    }
}