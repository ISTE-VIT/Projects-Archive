package com.example.gtu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Objects;

public class ajava_list extends AppCompatActivity implements View.OnClickListener {

    CardView chap1,chap2,chap3,chap4,chap5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second_screen);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_ajava_list);

        chap1 = findViewById(R.id.chap1);
        chap2 = findViewById(R.id.chap2);
        chap3 = findViewById(R.id.chap3);
        chap4 = findViewById(R.id.chap4);
        chap5 = findViewById(R.id.chap5);

        chap1.setOnClickListener(this);
        chap2.setOnClickListener(this);
        chap3.setOnClickListener(this);
        chap4.setOnClickListener(this);
        chap5.setOnClickListener(this);

        Button back = findViewById(R.id.back);

        back.setOnClickListener(arg ->
        {
            Intent i1 = new Intent(this,mcq.class);
            startActivity(i1);
        });

    }

    @Override
    public void onClick(View v) {

      String res = "";

        if(v.getId() == R.id.chap1)
        {
            res = "one";
            Intent i =  new Intent(ajava_list.this,mcq.class);
            i.putExtra("res",res);
            startActivity(i);
        }
        else if(v.getId() == R.id.chap2)
        {
            res = "two";
            Intent i =  new Intent(ajava_list.this,mcq.class);
            i.putExtra("res",res);
            startActivity(i);
        }
        else if(v.getId() == R.id.chap3)
        {
            res = "three";
            Intent i =  new Intent(ajava_list.this,mcq.class);
            i.putExtra("res",res);
            startActivity(i);
        }
        else if(v.getId() == R.id.chap4)
        {
            res = "four";
            Intent i =  new Intent(ajava_list.this,mcq.class);
            i.putExtra("res",res);
            startActivity(i);
        }
        else if(v.getId() == R.id.chap5)
        {
            res = "five";
            Intent i =  new Intent(ajava_list.this,mcq.class);
            i.putExtra("res",res);
            startActivity(i);
        }


//        switch (v.getId())
//        {
//            case R.id.chap1 :
//                Intent i =  new Intent(this,mcq.class);
//               i.putExtra("chap1","one");
//                startActivity(i);
//                break;
//
//            case R.id.chap2 :
//                  i = new Intent(this,mcq.class);
//                 i.putExtra("chap2","two");
//                startActivity(i);
//                break;
//
//            case R.id.chap3 :
//                i = new Intent(this,mcq.class);
//                i.putExtra("chap3","three");
//                startActivity(i);
//                break;
//        }
    }
}