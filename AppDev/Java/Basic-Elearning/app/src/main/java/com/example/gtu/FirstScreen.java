package com.example.gtu;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class FirstScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Objects.requireNonNull(getSupportActionBar()).hide();

        setContentView(R.layout.activity_main);

        //This is for go to one screen from another automatically
        Thread t = new Thread()
        {
            public void run()
            {
                try {
                    sleep(800);
                }catch (Exception e)
                {
                   e.printStackTrace();
                }
                finally {
                    Intent i = new Intent(FirstScreen.this,SecondScreen.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        t.start();
        //------------------------------------------------------------------------------------------//



    }
}