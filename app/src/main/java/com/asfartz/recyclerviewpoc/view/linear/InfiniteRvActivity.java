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
import com.asfartz.recyclerviewpoc.adapters.NoteAdapterInfinite;
import com.asfartz.recyclerviewpoc.view.grid.GridHRvActivity;
import com.asfartz.recyclerviewpoc.view.grid.GridVRvActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class InfiniteRvActivity extends AppCompatActivity {

    private static final String TAG = "InfiniteRvActivity";

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private NoteAdapterInfinite noteAdapter;
    private FloatingActionButton addBtn;
    List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infinite_rv);

        addBtn = findViewById(R.id.infinite_rv_add_button);
        recyclerView = findViewById(R.id.infinite_rv);

        initList();
        initAddBtn();
        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        noteAdapter = new NoteAdapterInfinite(notes);

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
        notes.add(new Note("Title 6", "Description 6"));
        notes.add(new Note("Title 7", "Description 7"));
        notes.add(new Note("Title 8", "Description 8"));
    }

    private void initAddBtn() {
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
                return true;

            case R.id.menu_reverse_rv:
                Intent infiniteRvIntent = new Intent(InfiniteRvActivity.this, ReverseRvActivity.class);
                startActivity(infiniteRvIntent);
                finish();
                return true;

            case R.id.menu_horizontal_rv:
                Intent horizontalRvIntent = new Intent(InfiniteRvActivity.this, HorizontalRvActivity.class);
                startActivity(horizontalRvIntent);
                finish();
                return true;

            case R.id.menu_grid_v_rv:
                Intent gridVIntent = new Intent(InfiniteRvActivity.this, GridVRvActivity.class);
                startActivity(gridVIntent);
                finish();
                return true;

            case R.id.menu_grid_h_rv:
                Intent gridHRvIntent = new Intent(InfiniteRvActivity.this, GridHRvActivity.class);
                startActivity(gridHRvIntent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
