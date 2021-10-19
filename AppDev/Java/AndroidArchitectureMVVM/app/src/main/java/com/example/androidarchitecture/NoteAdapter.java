package com.example.androidarchitecture;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private List<Note> notes= new ArrayList<>();
    private onItemClickListener listener;

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote= notes.get(position);
        holder.title.setText(currentNote.getTitle());
        holder.description.setText(currentNote.getDescription());
        holder.priority.setText(String.valueOf(currentNote.getPriority()));

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes)
    {
        this.notes= notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position)
    {
        return notes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder
    {
        private final TextView title;
        private final TextView description;
        private final TextView priority;
        public NoteHolder(View itemView)
        {
            super(itemView);
            title= itemView.findViewById(R.id.title);
            description= itemView.findViewById(R.id.description);
            priority= itemView.findViewById(R.id.priority);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position= getAdapterPosition();
                    listener.onItemClick(notes.get(position));
                }
            });
        }

    }
    public interface onItemClickListener{
        void onItemClick(Note note);
    }
    public void setOnItemClickListener(onItemClickListener listener){
        this.listener= listener;
    }
}

