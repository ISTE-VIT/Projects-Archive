package com.example.androidarchitecture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class ActivtiyAddEditNote extends AppCompatActivity {

    public static final String EXTRA_TITLE =
            "com.example.androidarchitecture.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.androidarchitecture.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "com.example.androidarchitecture.EXTRA_PRIORITY";
    public static final String EXTRA_ID =
            "com.example.androidarchitecture.EXTRA_ID";

    EditText editTextTitle;
    EditText editTextDescription;
    NumberPicker numberPickerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activtiy_add_note);

        editTextTitle= findViewById(R.id.edit_text_title);
        editTextDescription= findViewById(R.id.edit_text_description);
        numberPickerPriority= findViewById(R.id.number_picker_priority);

        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);
        Intent intent= getIntent();

        if (intent.hasExtra(EXTRA_ID)){
            setTitle("EDIT THIS NOTE");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        }else {
            getSupportActionBar().setTitle("ADD NOTE");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.save:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote() {
        String title= editTextTitle.getText().toString();
        String description= editTextDescription.getText().toString();
        int priority= numberPickerPriority.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty())
        {
            Toast.makeText(this, "Please fill all details!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data= new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id= getIntent().getIntExtra(EXTRA_ID, -1);
        if (id!= -1){
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();

    }
}