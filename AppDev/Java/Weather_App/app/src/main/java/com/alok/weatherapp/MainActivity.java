package com.alok.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public EditText cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void searchButtonClicked(View view) {
        cityName = findViewById(R.id.cityName);
        String message = cityName.getText().toString();
        Intent intent = new Intent(MainActivity.this, WeatherInformation.class); //in (from,to)
        intent.putExtra("key", message);
        startActivity(intent);

    }
}