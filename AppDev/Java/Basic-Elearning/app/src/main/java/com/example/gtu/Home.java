package com.example.gtu;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;
public class Home extends AppCompatActivity implements View.OnClickListener {
    CardView ajava,nma,mcom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second_screen);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_home);
        ajava = findViewById(R.id.ajava);
        nma = findViewById(R.id.nma);
        mcom = findViewById(R.id.mcom);
        ajava.setOnClickListener(this);
        nma.setOnClickListener(this);
        mcom.setOnClickListener(this);
        Button ad = findViewById(R.id.ad);
        ad.setOnClickListener(arg ->
        {
            Toast.makeText(this, "AD Will Play ", Toast.LENGTH_SHORT).show();
        });
    }
    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId())
        {
            case R.id.ajava : i = new Intent(this,ajava_list.class);
            startActivity(i);

            break;

            case R.id.nma : i = new Intent(this,nma_list.class);
                startActivity(i);

                break;

            case R.id.mcom : i = new Intent(this,mcom_list.class);
                startActivity(i);

                break;
        }
    }
}