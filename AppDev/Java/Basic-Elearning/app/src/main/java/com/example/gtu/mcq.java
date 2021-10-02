package com.example.gtu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

public class mcq extends AppCompatActivity {

    PDFView chap1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcq);

        chap1 = findViewById(R.id.mcq);

        Bundle extras = getIntent().getExtras();

        String result = extras.getString("res");

        switch (result)
        {
            case "one" : chap1.fromAsset("ch1.pdf").load(); break;
            case "two" : chap1.fromAsset("ch2.pdf").load();break;
            case "three" : chap1.fromAsset("ch3.pdf").load();break;
            case "four" : chap1.fromAsset("ch4.pdf").load();break;
            case "five" : chap1.fromAsset("ch5.pdf").load();break;

        }











    }
}