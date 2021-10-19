package com.example.androidarchitecture;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.androidarchitecture.ActivtiyAddEditNote.EXTRA_ID;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_NOTE_REQUEST= 1;
    public static final int EDIT_NOTE_REQUEST= 2;
    private NoteViewModel noteViewModel;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        floatingActionButton= findViewById(R.id.button_add_note);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, ActivtiyAddEditNote.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });

        NoteAdapter noteAdapter= new NoteAdapter();
        recyclerView.setAdapter(noteAdapter);

        noteViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(NoteViewModel.class);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                noteAdapter.setNotes(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                noteViewModel.delete(noteAdapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Note Deleted!", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        noteAdapter.setOnItemClickListener(new NoteAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                Intent intent= new Intent(MainActivity.this, ActivtiyAddEditNote.class);
                intent.putExtra(EXTRA_ID, note.getId());
                intent.putExtra(ActivtiyAddEditNote.EXTRA_TITLE, note.getTitle());
                intent.putExtra(ActivtiyAddEditNote.EXTRA_DESCRIPTION, note.getDescription());
                intent.putExtra(ActivtiyAddEditNote.EXTRA_PRIORITY, note.getPriority());
                startActivityForResult(intent, EDIT_NOTE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK)
        {
            String title= data.getStringExtra(ActivtiyAddEditNote.EXTRA_TITLE);
            String description= data.getStringExtra(ActivtiyAddEditNote.EXTRA_DESCRIPTION);
            int priority= data.getIntExtra(ActivtiyAddEditNote.EXTRA_PRIORITY, 1);

            Note note= new Note(title, description, priority);
            noteViewModel.insert(note);
        }
        else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK){
            int id= data.getIntExtra(EXTRA_ID, -1);
            if (id == -1){
                Toast.makeText(this, "Note can't be updated!", Toast.LENGTH_SHORT).show();
                return;
            }
            String title= data.getStringExtra(ActivtiyAddEditNote.EXTRA_TITLE);
            String description= data.getStringExtra(ActivtiyAddEditNote.EXTRA_DESCRIPTION);
            int priority= data.getIntExtra(ActivtiyAddEditNote.EXTRA_PRIORITY, 1);

            Note note= new Note(title, description, priority);
            note.setId(id);
            noteViewModel.update(note);

            Toast.makeText(this, "Note updated!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Note not saved!!", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.delete_all_notes:
                noteViewModel.deleteAllNotes();
                Toast.makeText(this, "Deleted All Notes!!", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }
}