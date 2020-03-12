package com.asfartz.recyclerviewpoc.view.linear;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asfartz.recyclerviewpoc.Note;
import com.asfartz.recyclerviewpoc.R;
import com.asfartz.recyclerviewpoc.adapters.NoteAdapter;
import com.asfartz.recyclerviewpoc.view.grid.GridHRvActivity;
import com.asfartz.recyclerviewpoc.view.grid.GridVRvActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HorizontalRvActivity extends AppCompatActivity {

    private static final String TAG = "HorizontalRvActivity";

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private NoteAdapter noteAdapter;
    private FloatingActionButton addBtn;
    List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_rv);

        addBtn = findViewById(R.id.horizontal_rv_add_button);
        recyclerView = findViewById(R.id.horizontal_rv);

        initList();
        initAddBtn();

        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        noteAdapter = new NoteAdapter(notes);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(noteAdapter);
    }

    private void initList() {
        notes = new ArrayList<>();
        notes.add(new Note("Title 1", "Description 1"));
        notes.add(new Note("Title 2", "Description 2"));
        notes.add(new Note("Title 3", "Description 3"));
        notes.add(new Note("Title 4", "Description 4"));
        notes.add(new Note("Title 5", "Description 5"));
    }

    private void initAddBtn(){
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nr = notes.size() + 1;
                noteAdapter.addItem(new Note("Title " + nr, "Description " + nr));
                recyclerView.smoothScrollToPosition(noteAdapter.getItemCount() - 1);   // " - 1 " because positions start from 0
                Log.d(TAG, "size = " + noteAdapter.getItemCount() + ", smooth scroll to: " + (noteAdapter.getItemCount() - 1));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_recyclerviews, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_infinite_rv:
                Intent infiniteRvIntent = new Intent(HorizontalRvActivity.this, InfiniteRvActivity.class);
                startActivity(infiniteRvIntent);
                finish();
                return true;

            case R.id.menu_reverse_rv:
                Intent reverseRvIntent = new Intent(HorizontalRvActivity.this, ReverseRvActivity.class);
                startActivity(reverseRvIntent);
                finish();
                return true;

            case R.id.menu_horizontal_rv:
                return true;

            case R.id.menu_grid_v_rv:
                Intent gridVIntent = new Intent(HorizontalRvActivity.this, GridVRvActivity.class);
                startActivity(gridVIntent);
                finish();
                return true;

            case R.id.menu_grid_h_rv:
                Intent gridHRvIntent = new Intent(HorizontalRvActivity.this, GridHRvActivity.class);
                startActivity(gridHRvIntent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
