package com.example.prac14;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button button,button2,button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button.setOnClickListener(arg ->
                {
                    textView.setTextColor(Color.parseColor("#ff0000"));
                }
                );
        button2.setOnClickListener(arg ->
        {
            textView.setTextColor(Color.parseColor("#00ff00"));
        });
        button3.setOnClickListener(arg ->
        {
            textView.setTextColor(Color.parseColor("#0000ff"));
        });

    }
}