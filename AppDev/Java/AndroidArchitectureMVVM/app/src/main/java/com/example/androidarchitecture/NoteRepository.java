package com.example.androidarchitecture;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Update;

import java.util.List;

public class NoteRepository {
    private final NoteDao noteDao;

    private final LiveData<List<Note>> allNotes;

    public NoteRepository(Application application)
    {
        NoteDatabase database= NoteDatabase.getInstance(application);
        noteDao= database.noteDao();
        allNotes= noteDao.getAllNotes();
    }
    public void insert(Note note)
    {
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    public void update(Note note)
    {
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note)
    {
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    public void deleteAllNotes()
    {
        new DeleteAllNodesNoteAsyncTask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void>{

        private final NoteDao noteDao;
        private InsertNoteAsyncTask(NoteDao noteDao)
        {
            this.noteDao= noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void>{

        private final NoteDao noteDao;
        private UpdateNoteAsyncTask(NoteDao noteDao)
        {
            this.noteDao= noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void>{

        private final NoteDao noteDao;
        private DeleteNoteAsyncTask(NoteDao noteDao)
        {
            this.noteDao= noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNodesNoteAsyncTask extends AsyncTask<Void, Void, Void>{

        private final NoteDao noteDao;
        private DeleteAllNodesNoteAsyncTask(NoteDao noteDao)
        {
            this.noteDao= noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }
}
