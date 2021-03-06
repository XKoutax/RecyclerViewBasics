package com.asfartz.recyclerviewpoc.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asfartz.recyclerviewpoc.Note;
import com.asfartz.recyclerviewpoc.R;

import java.util.List;

public class NoteAdapterInfinite extends RecyclerView.Adapter<NoteAdapterInfinite.mNoteViewHolder> {

    private final List<Note> notes;

    public NoteAdapterInfinite(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public mNoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_model_row, parent, false);

        mNoteViewHolder vh = new mNoteViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(mNoteViewHolder holder, int position) {
        int positionInList = position % notes.size();
        holder.tvTitle.setText(notes.get(positionInList).getTitle());
        holder.tvDesc.setText(notes.get(positionInList).getDescription());
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }



    public void addItem(Note item) {
        notes.add(item);
        this.notifyDataSetChanged();
    }

    public static class mNoteViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvDesc;


        mNoteViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_descr);

        }


    }
}