package com.example.numbershape;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   class checker
    {
    int number;
    public boolean istriangle(){
        int x=1;
        int trinum=1;
        while(trinum<number){
            x++;
            trinum+=x;
        }
        return trinum == number;
    }
    }
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click(View view)
        {
        EditText one=findViewById(R.id.editText);

        checker check= new checker();
        check.number=Integer.parseInt(one.getText().toString());
        if(check.istriangle()){
            Toast.makeText(this,"it is a triangle num!",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"not a triangle num",Toast.LENGTH_SHORT).show();
        }
        }

}